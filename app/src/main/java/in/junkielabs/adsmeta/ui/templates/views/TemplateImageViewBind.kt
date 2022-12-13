package `in`.junkielabs.adsmeta.ui.templates.views

import `in`.junkielabs.adsmeta.databinding.TemplateImageviewBinding
import `in`.junkielabs.adsmeta.ui.labs.json.model.Model2DNode
import `in`.junkielabs.adsmeta.ui.templates.views.base.TemplateViewBindBase
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load

/**
 * Created by Niraj on 13-12-2022.
 */
class TemplateImageViewBind(id: Int,  pModel: Model2DNode): TemplateViewBindBase<TemplateImageviewBinding>(id, pModel) {


    override fun inflateView(context: Context, parent: ConstraintLayout): TemplateImageviewBinding {
        return TemplateImageviewBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    }

    override fun attachAttributes(
        context: Context,
        pModel: Model2DNode,
        binding: TemplateImageviewBinding
    ) {
        super.attachAttributes(context, pModel, binding)
        Log.d("TemplateImageViewBind 1", "attachAttributes: ${pModel.cns}")
        if(pModel.cns!=null){

            Log.d("TemplateImageViewBind 2", "attachAttributes: ${pModel.cns}")
            binding.root.load(pModel.cns.imageSrc)

        }
    }

  /*  override fun attachAttributes(context: Context, pModel: Model2DNode) {
        super.attachAttributes(context, pModel, )

        if(pModel.cns!=null){
            vBinding.labsTemplateFragmentIv.load("file:///android_asset/cap.PNG")

        }
    }*/
}