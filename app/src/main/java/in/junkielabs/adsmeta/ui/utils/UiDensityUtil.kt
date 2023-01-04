package `in`.junkielabs.adsmeta.ui.utils

import android.content.Context

/**
 * Created by Niraj on 12-12-2022.
 */
object UiDensityUtil {
        /**
         * dip to px
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        /**
         * px to dp
         */
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }
}