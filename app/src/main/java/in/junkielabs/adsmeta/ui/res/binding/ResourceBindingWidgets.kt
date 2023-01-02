package `in`.junkielabs.adsmeta.ui.res.binding

import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView

/**
 * Created by Niraj on 14-05-2021.
 */

object ResourceBindingWidgets {


    @BindingAdapter("app:bindBackgroundColor")
    @JvmStatic
    fun MaterialCardView.setCardBgColor(color: String?){

        setCardBackgroundColor(Color.parseColor(color?:"#00000000"))
    //= ContextCompat.getColorStateList(context, color)
    }


}

