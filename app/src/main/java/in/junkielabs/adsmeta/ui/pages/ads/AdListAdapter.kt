package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.R
import `in`.junkielabs.adsmeta.data.base.LocalResult
import `in`.junkielabs.adsmeta.databinding.AdChipFullBinding
import `in`.junkielabs.adsmeta.databinding.AdChipHalfBinding
import `in`.junkielabs.adsmeta.databinding.AdItemFullBinding
import `in`.junkielabs.adsmeta.databinding.AdItemHalfBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdItem
import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.Tag
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color.parseColor
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.ar.schemas.lull.Color
import kotlinx.coroutines.NonDisposableHandle.parent
import java.io.File
// https://stackoverflow.com/questions/72717784/using-spansizelookup-in-kotlin-to-set-span-size-based-on-itemviewtype-in-gridlay
class AdListAdapter :
    ListAdapter<ModelAdItem, RecyclerView.ViewHolder>(ModelAdItem.diffCallback) {

    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
        return super.getItem(position).spanCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return ItemViewHolder.fromHalf(parent)
        return if (viewType == 2) {
            ItemViewHolder.fromFull(parent)
        } else {
            ItemViewHolder.fromHalf(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder.Half) {
            holder.bind(getItem(position))
        }
        if (holder is ItemViewHolder.Full) {
            holder.bind(getItem(position))
        }
    }

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ad_item_half, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adItemList = adList[position]
        holder.title.text = adItemList.title
//        holder.imageView.setImageResource(adItemList.cns.imageSrc)


    }

    override fun getItemCount(): Int {
        return adList.size
    }

        class ItemViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)


    }
    */


    //    ExploreViewHolder
    sealed class ItemViewHolder<out T : ViewDataBinding>(val binding: T) :
        RecyclerView.ViewHolder(binding.root) {
        class Half(binding: AdItemHalfBinding) :
            ItemViewHolder<AdItemHalfBinding>(binding) {
            override fun bind(data: ModelAdItem) {
                binding.bModel = data
                binding.executePendingBindings()
                binding.cardViewHalf.setCardBackgroundColor(data.color.toColorInt())
                adImage(binding.adItemImage, data.cns.imageSrc)
                chipHalf(binding.adItemChipGroup, data.tags)

            }
        }

        class Full(binding: AdItemFullBinding) :
            ItemViewHolder<AdItemFullBinding>(binding) {
            override fun bind(data: ModelAdItem) {
                binding.bModel = data
                binding.executePendingBindings()
                binding.cardViewFull.setCardBackgroundColor(data.color.toColorInt())
                adImage(binding.adItemImage, data.cns.imageSrc)
                chipFull(binding.adItemChipGroup, data.tags)
            }
        }

        abstract fun bind(data: ModelAdItem)

        fun chipHalf(chipGroup: ChipGroup,tags: List<Tag>){
            tags.forEach {
                val chip = AdChipHalfBinding.inflate(
                    LayoutInflater.from(itemView.context),
                    chipGroup,
                    false).root

                chip.text = it.name
                chipGroup.addView(chip)

            }
        }
        fun chipFull(chipGroup: ChipGroup, tags: List<Tag>){
            tags.forEach{
                val chip = AdChipFullBinding.inflate(
                    LayoutInflater.from(itemView.context),
                    chipGroup,
                    false).root
                chip.text = it.name
                chip.chipBackgroundColor = ColorStateList.valueOf(it.color.toColorInt())
                chipGroup.addView(chip)
            }
        }

//        fun tags(chipGroup: ChipGroup, tags: List<Tag>) {
//
//            for (tag in tags) {
//                val chip = Chip(itemView.context, null, R.style.Widget_Jl_App_Chip)
//                chip.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//                chip.text = tag.name
//                chipGroup.addView(chip)
//            }
//        }

        fun adImage(adItemImage: ImageView, image: String) {
            adItemImage.load(image) {
                error(R.drawable.ic_launcher_foreground)
            }
        }

        companion object {
            fun fromHalf(parent: ViewGroup): Half {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdItemHalfBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder.Half(binding)
            }

            fun fromFull(parent: ViewGroup): Full {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdItemFullBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder.Full(binding)
            }
        }

    }

    /*sealed class ItemViewHolder<out T: ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {
        class Half(binding: AdItemHalfBinding) :
            ItemViewHolder<AdItemHalfBinding>(binding)
    }*/


/*    class HalfViewHolder private constructor(val binding: AdItemHalfBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = itemView.findViewById(R.id.title_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

//        val sds = ItemViewHolder.Half
        companion object{
            fun from(parent: ViewGroup): HalfViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdItemHalfBinding.inflate(layoutInflater, parent, false)
                return HalfViewHolder(binding)
            }
        }

    }*/


}

