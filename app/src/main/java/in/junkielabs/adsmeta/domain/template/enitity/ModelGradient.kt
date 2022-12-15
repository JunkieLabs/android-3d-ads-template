package `in`.junkielabs.adsmeta.domain.template.enitity

import android.graphics.drawable.GradientDrawable

/**
 * Created by Niraj on 15-12-2022.
 */
data class ModelGradient(val colors: Array<String> = arrayOf(), val orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModelGradient

        if (!colors.contentEquals(other.colors)) return false
        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = colors.contentHashCode()
        result = 31 * result + orientation.hashCode()
        return result
    }
}