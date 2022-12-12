package `in`.junkielabs.adsmeta.ui.templates

import `in`.junkielabs.adsmeta.databinding.TemplateViewBinding
import `in`.junkielabs.adsmeta.ui.labs.json.model.Model2DNode
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * Created by Niraj on 12-12-2022.
 */
class TemplateBinder(var adTemplate: ModelAdTemplate) {

    var id = 1;
    var mMap = hashMapOf<Int, Model2DNode>()

    fun bindView(context: Context, root: ConstraintLayout){

        val set = ConstraintSet()
        set.clone(root)
        for (model in adTemplate.backgrounds){
            bindView(context, root,set, model)
        }
    }
    private fun bindView(context: Context, root: ConstraintLayout,set: ConstraintSet, model: Model2DNode) {

        TemplateViewBinding.inflate(
            LayoutInflater.from(context),
            root,
            false
        ).also{


            it.root.id = id// = id;
            mMap[id] = model

            root.addView(it.root)
            set.connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 20)
            set.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 20) // Add top constraint to your view 1
            set.connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 20) // Optional if you want symmetry of view 1 in layout
            set.connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 20) // Optional if you want symmetry of view 1 in layout
            set.applyTo(root)

            if(model.color!=null){
                it.root.setBackgroundColor(Color.parseColor(model.color))
            }

            if(model.bound!=null){
                var bound = model.bound
                bound.height?.let {  bHeight ->
                    if (bHeight.type == TemplateConstants.Bound.DIMENSION_TYPE_DP && bHeight.value >= 0) {
                        set.constrainHeight(id,
                            (bHeight.value * context.resources.displayMetrics.density).toInt()
                        )
                    }
                }
                bound.width?.let {  bDimen ->
                    if (bDimen.type == TemplateConstants.Bound.DIMENSION_TYPE_DP && bDimen.value >= 0) {
                        set.constrainWidth(id,
                            (bDimen.value * context.resources.displayMetrics.density).toInt()
                        )
                    }
                }
            }




            id++;

        }

        /*
        TargetAreaBinding.inflate(
                LayoutInflater.from(this),
                binding.activityKnowMoreTargetFlexbox,
                false
            ).also {

        * */

    }

//    private fun setBound(root: ConstraintLayout, int id, root: )
}