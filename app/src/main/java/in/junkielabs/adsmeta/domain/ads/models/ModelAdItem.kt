package `in`.junkielabs.adsmeta.domain.ads.models

import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.Cns
import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.Tag
import androidx.recyclerview.widget.DiffUtil

data class ModelAdItem(
    val key: String,
    val cns: Cns,
    val color: String,
    val spanCount: Int,
    val tags: List<Tag>,
    val title: String
){
    companion object {
        var diffCallback: DiffUtil.ItemCallback<ModelAdItem> =
            object : DiffUtil.ItemCallback<ModelAdItem>() {

                override fun areItemsTheSame(
                    oldItem: ModelAdItem,
                    newItem: ModelAdItem
                ): Boolean {
                    return oldItem.key == newItem.key
                }

                override fun areContentsTheSame(
                    oldItem: ModelAdItem,
                    newItem: ModelAdItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}