package `in`.junkielabs.adsmeta.ui.labs.json

import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream
import kotlin.jvm.Throws

object Utils {
    fun readJson(context: Context): String {
        Log.e("readJson","read json worked")
        return getJsonToInputStream(context).bufferedReader().use { it.readText() }
    }

    @Throws(IOException::class)
    fun getJsonToInputStream(context: Context): InputStream {
        Log.e("inputStream","input Stream worked")
        return context.assets.open("data/ad1.json")
    }
}