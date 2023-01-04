package `in`.junkielabs.adsmeta.ui.templates.views

import `in`.junkielabs.adsmeta.databinding.TemplateImageviewBinding
import `in`.junkielabs.adsmeta.domain.template.enitity.Model2DNode
import `in`.junkielabs.adsmeta.ui.templates.views.base.TemplateViewBindBase
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.ImageLoader
import coil.decode.SvgDecoder
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

            if(pModel.cns.scaleType=="cover"){
                binding.root.scaleType = ImageView.ScaleType.CENTER_CROP
            }else{
                binding.root.scaleType = ImageView.ScaleType.FIT_CENTER

            }
            if(pModel.cns.imageSrc!=null && pModel.cns.imageSrc.endsWith(".svg")){

                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        add(SvgDecoder.Factory())
                    }
                    .build()

                binding.root.load(pModel.cns.imageSrc, imageLoader)

            }else{

                binding.root.load(pModel.cns.imageSrc)

            }

        }
    }

  /*  override fun attachAttributes(context: Context, pModel: Model2DNode) {
        super.attachAttributes(context, pModel, )

        if(pModel.cns!=null){
            vBinding.labsTemplateFragmentIv.load("file:///android_asset/cap.PNG")

        }
    }*/
}