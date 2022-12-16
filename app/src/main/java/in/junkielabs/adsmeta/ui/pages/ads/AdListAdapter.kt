package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.Item
import android.widget.ImageView

class AdListAdapter(private val adList: List<Item>) :
    RecyclerView.Adapter<AdListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)


    }
}

