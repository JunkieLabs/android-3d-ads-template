package `in`.junkielabs.adsmeta.ui.labs.json

import `in`.junkielabs.adsmeta.databinding.LabsJsonFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.json.Utils.readJson
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class LabsFragmentJson: FragmentBase(true) {

    private lateinit var vBinding: LabsJsonFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding = LabsJsonFragmentBinding.inflate(inflater, container, false).apply {}
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val jsonString = readJson(requireContext())
        vBinding.textView.text = jsonString
        Log.d("LabsFragmentJson", "jsonString: $jsonString")



        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<ModelAdTemplate> = moshi.adapter(ModelAdTemplate::class.java)
        val moshiResult = jsonAdapter.fromJson(jsonString)
        Log.d("LabsFragmentJson","moshiResult: $moshiResult")


    }

}