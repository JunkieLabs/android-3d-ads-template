package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.R
import `in`.junkielabs.adsmeta.data.base.LocalResult
import `in`.junkielabs.adsmeta.databinding.AdItemFullBinding
import `in`.junkielabs.adsmeta.databinding.AdItemHalfBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdItem
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

class AdListAdapter :
   ListAdapter<ModelAdItem, RecyclerView.ViewHolder>(ModelAdItem.diffCallback) {

    override fun getItemViewType(position: Int): Int {
//        TODO("Not yet implemented")
             return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        TODO("Not yet implemented")
        return ItemViewHolder.fromHalf(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder.Half){
            holder.bind(getItem(position))
        }
        if(holder is ItemViewHolder.Full){
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

    sealed class ItemViewHolder<out T: ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {
        class Half(binding: AdItemHalfBinding) :
            ItemViewHolder<AdItemHalfBinding>(binding) {
            override fun bind(data: ModelAdItem) {
                binding.bModel = data
                binding.executePendingBindings()
                }

        }
        class Full(binding: AdItemFullBinding) :
            ItemViewHolder<AdItemFullBinding>(binding) {
            override fun bind(data: ModelAdItem) {
                binding.bModel = data
                binding.executePendingBindings()
            }

        }

        abstract fun bind(data: ModelAdItem)

        companion object{
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

