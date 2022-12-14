package `in`.junkielabs.adsmeta.ui.templates.views.base

import `in`.junkielabs.adsmeta.ui.labs.json.model.Model2DNode
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelBoundAlignment
import `in`.junkielabs.adsmeta.ui.templates.TemplateConstants
import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewbinding.ViewBinding

/**
 * Created by Niraj on 13-12-2022.
 */
abstract class TemplateViewBindBase<Binding : ViewBinding>(var id: Int, var model: Model2DNode) {


    private var vBinding: Binding? = null

    fun bind(context: Context, parent: ConstraintLayout, set: ConstraintSet) {

        vBinding = inflateView(context, parent)

        if (vBinding == null) return
        vBinding!!.root.id = id// = id;

        parent.addView(vBinding!!.root)
        if (model.bound != null) {
            var bound = model.bound
            bound!!.height?.let { bHeight ->
                if (bHeight.type == TemplateConstants.Bound.DIMENSION_TYPE_DP) {

                    if( bHeight.value >= 0){
                        set.constrainHeight(
                            id,
                            (bHeight.value * context.resources.displayMetrics.density).toInt()
                        )
                    }else {
                        set.constrainHeight( id, bHeight.value)
                    }

                } else if (bHeight.type == TemplateConstants.Bound.DIMENSION_TYPE_PERCENT) {
                    set.constrainPercentHeight(
                        id,
                        bHeight.value.toFloat() / 100f
                    )
                }
            }
            bound.width?.let { bDimen ->
                if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_DP ) {
                    if(bDimen.value >= 0){
                        set.constrainWidth(
                            id,
                            (bDimen.value * context.resources.displayMetrics.density).toInt()
                        )
                    }else {
                        set.constrainWidth( id, bDimen.value)
                    }

                } else if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_PERCENT) {
                    set.constrainPercentWidth(
                        id,
                        bDimen.value.toFloat() / 100f
                    )

                }
            }

            bound.positionY?.let { bDimen ->
                if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_DP ) {

//                    set.setMargin(id, ConstraintSet.TOP, (bDimen.value * context.resources.displayMetrics.density))

                }else if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_PERCENT){

                    Log.d("TemplateViewBindBase", "bind positionY: $bDimen")

                    set.setVerticalBias( id,
                        bDimen.value.toFloat() / 100f)
                }
            }
            bound.positionX?.let { bDimen ->
                if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_DP ) {

//                    set.setMargin(id, ConstraintSet.TOP, (bDimen.value * context.resources.displayMetrics.density))

                }else if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_PERCENT){

                    Log.d("TemplateViewBindBase", "bind positionX: $bDimen")

                    set.setHorizontalBias( id,
                        bDimen.value.toFloat() / 100f)
                }
            }

            attachAlignments(model.bound!!.alignSelf, set)

        }
        set.applyTo(parent)

        vBinding?.let {
            attachAttributes(context, model, it)
        }
    }

    abstract fun inflateView(context: Context, parent: ConstraintLayout): Binding;

    /*{
        return TemplateViewBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    }*/

    fun getBinding(): Binding? {
        return vBinding
    }

    open fun attachAttributes(context: Context, pModel: Model2DNode, binding: Binding) {
        Log.d("TemplateViewBindBase :", "attachAttributes: ${pModel.cns}")
        if (model.color != null) {
            binding.root.setBackgroundColor(Color.parseColor(pModel.color))
        }
    }

    private fun attachAlignments(aligns: List<ModelBoundAlignment>, set: ConstraintSet){
        set.connect(
            id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            0
        )
        set.connect(
            id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        ) // Add top constraint to your view 1
        set.connect(
            id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END,
            0
        ) // Optional if you want symmetry of view 1 in layout
        set.connect(
            id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            0
        ) // Optional if you want symmetry of view 1 in layout

        if (aligns.isNotEmpty()) {
            for (align in aligns) {
                when (align.alignment) {
                    "bottom" -> {
                        set.clear(id, ConstraintSet.TOP)
                    }
                    "top" -> {
                        set.clear(id, ConstraintSet.BOTTOM)
                    }
                    "start" -> {
                        set.clear(id, ConstraintSet.END)
                    }
                    "end" -> {
                        set.clear(id, ConstraintSet.START)
                    }
                }
            }
        }
    }

}