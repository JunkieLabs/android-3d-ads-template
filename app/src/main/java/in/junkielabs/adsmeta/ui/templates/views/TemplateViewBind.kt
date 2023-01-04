package `in`.junkielabs.adsmeta.ui.templates.views

import `in`.junkielabs.adsmeta.databinding.TemplateViewBinding
import `in`.junkielabs.adsmeta.domain.template.enitity.Model2DNode
import `in`.junkielabs.adsmeta.ui.templates.views.base.TemplateViewBindBase
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by Niraj on 13-12-2022.
 */
class TemplateViewBind(id: Int,  pModel: Model2DNode): TemplateViewBindBase<TemplateViewBinding>(id, pModel) {


    override fun inflateView(context: Context, parent: ConstraintLayout): TemplateViewBinding {
        return TemplateViewBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    }

  /*  override fun attachAttributes(context: Context, pModel: Model2DNode) {
        super.attachAttributes(context, pModel, )

        if(pModel.cns!=null){
            vBinding.labsTemplateFragmentIv.load("file:///android_asset/cap.PNG")

        }
    }*/
}