package `in`.junkielabs.adsmeta.data.local.ads

import android.content.Context
import java.io.IOException
import java.io.InputStream
import kotlin.jvm.Throws

object Utils {
    fun readJson(context: Context, filePath: String = "data/ad1.json"): String {
        return getJsonToInputStream(context, filePath).bufferedReader().use { it.readText() }
    }

    @Throws(IOException::class)
    fun getJsonToInputStream(context: Context, adFilePath: String): InputStream {
        return context.assets.open(adFilePath)
    }
}