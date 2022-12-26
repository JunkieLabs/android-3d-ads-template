package `in`.junkielabs.adsmeta.ui.templates

import `in`.junkielabs.adsmeta.ui.labs.json.model.Model2DNode
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import `in`.junkielabs.adsmeta.ui.labs.three.sense.Labs3dSenseRotation
import `in`.junkielabs.adsmeta.ui.templates.views.TemplateImageViewBind
import `in`.junkielabs.adsmeta.ui.templates.views.TemplateViewBind
import `in`.junkielabs.adsmeta.ui.templates.views.base.TemplateViewBindBase
import android.content.Context
import android.util.Log
import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewbinding.ViewBinding

/**
 * Created by Niraj on 12-12-2022.
 */
class TemplateBinder(var adTemplate: ModelAdTemplate) {

    var id = 1;
    var mMap = hashMapOf<Int, Model2DNode>()
    var backgroundBindings = mutableListOf<TemplateViewBindBase<out ViewBinding>>()
    var objectBindings = mutableListOf<TemplateViewBindBase<out ViewBinding>>()

    fun bindBackView(context: Context, root: ConstraintLayout){

        val set = ConstraintSet()
        set.clone(root)
        for (model in adTemplate.backgrounds){
            // TODO bindView(context, root,set, model)

            Log.d("Template Binder", "bindView: $model ")
            if(model.cns!=null){
                backgroundBindings.add(TemplateImageViewBind(id, model ))
            }else {
                backgroundBindings.add(TemplateViewBind(id, model ))

            }
            mMap[id] = model

            id++
        }
        for (viewBind in backgroundBindings){
            viewBind.bind(context, root, set)
        }
    }

    fun bindObjectsView(context: Context, parent: ConstraintLayout) {
        val set = ConstraintSet()
        set.clone(parent)
        for (model in adTemplate.contentObjects){
            // TODO bindView(context, root,set, model)

            Log.d("Template Binder", "bindView: $model ")
            if(model.cns!=null){
                objectBindings.add(TemplateImageViewBind(id, model ))
            }else {
                objectBindings.add(TemplateViewBind(id, model ))

            }
            mMap[id] = model

            id++
        }
        for (viewBind in objectBindings){
            viewBind.bind(context, parent, set)
        }

    }

    var mSenseCalibration : Labs3dSenseRotation? = null

    fun onSense(context: Context, parent: ConstraintLayout, labs3dSenseRotation: Labs3dSenseRotation){
        if(mSenseCalibration == null){
            mSenseCalibration = labs3dSenseRotation
        }

        var rollDiff = labs3dSenseRotation.roll - (mSenseCalibration?.roll?:0.0f)
        var pitchDiff = labs3dSenseRotation.pitch - (mSenseCalibration?.pitch?:0.0f)

//        Log.d("TemplateBinder: ", "onSense: $rollDiff, $pitchDiff")
        val dm = context.resources.displayMetrics
        val xOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rollDiff*5, dm)
        val yOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pitchDiff*5, dm)

//        val set = ConstraintSet()
//        set.clone(parent)
        Log.d("TemplateBinder: ", "onSense: $xOffset, $yOffset")

        for (bind in objectBindings){

//            set.get
           var xMargin  = (mMap[bind.id]?.senseLevel?:0) * xOffset
            var yMargin  = (mMap[bind.id]?.senseLevel?:0) * yOffset
//            Log.d("TemplateBinder: ", "onSense: ${bind.getBinding()?.root}")

            bind.getBinding()?.root?.translationX = xMargin
            bind.getBinding()?.root?.translationY = -yMargin


//            set.setMargin(bind.id, ConstraintSet.START, xMargin.toInt())
//            set.setMargin(bind.id, ConstraintSet.BOTTOM, yMargin.toInt())
        }
//        set.applyTo(parent)
    }
    /*  private fun bindView(context: Context, root: ConstraintLayout,set: ConstraintSet, model: Model2DNode) {

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

          *//*
        TargetAreaBinding.inflate(
                LayoutInflater.from(this),
                binding.activityKnowMoreTargetFlexbox,
                false
            ).also {

        * *//*

    }*/

//    private fun setBound(root: ConstraintLayout, int id, root: )
}