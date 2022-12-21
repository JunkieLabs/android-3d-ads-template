package `in`.junkielabs.adsmeta.data.local.ads

import `in`.junkielabs.adsmeta.data.base.LocalResult
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import `in`.junkielabs.adsmeta.ui.labs.json.Utils
import android.content.Context
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Niraj on 21-12-2022.
 */

@Singleton
class LocalAdsSource @Inject constructor(@ApplicationContext var context: Context) {


    suspend fun  getAds() = withContext(Dispatchers.IO) {
        var data: ModelAdList? = null
        try {
            val jsonString = Utils.readJson(context, "data/ads.json")

            val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter: JsonAdapter<ModelAdList> = moshi.adapter(ModelAdList::class.java)
             data = jsonAdapter.fromJson(jsonString)

        }catch (
            e: Throwable
        ){
            e.printStackTrace()
            return@withContext LocalResult.Exception(e)

        }

        Log.d("LocalAdsSource", "getAds: ${data}")
        if(data!=null){
            return@withContext LocalResult.Success(data)
        }else {
            return@withContext LocalResult.Message(12, "Data Empty")

        }

    }


}