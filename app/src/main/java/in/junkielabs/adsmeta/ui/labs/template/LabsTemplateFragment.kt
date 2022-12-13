package `in`.junkielabs.adsmeta.ui.labs.template

import `in`.junkielabs.adsmeta.databinding.LabsTemplateFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.json.Utils
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import `in`.junkielabs.adsmeta.ui.templates.TemplateBinder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Niraj on 12-12-2022.
 */
class LabsTemplateFragment  : FragmentBase(true){

    private lateinit var vBinding: LabsTemplateFragmentBinding

    private var mTemplateBinder: TemplateBinder? =null

    private val mViewModel: LabsTemplateViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            LabsTemplateFragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
                lifecycleOwner = this@LabsTemplateFragment.viewLifecycleOwner
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        Asset
//        vBinding.labsTemplateFragmentIv.load("file:///android_asset/cap.PNG")
        lifecycleScope.launchWhenCreated {
            var template = getJson()

            mTemplateBinder = template?.let { TemplateBinder(it) }


            bindData()

//            Log.i("LabsTemplateFragment", "onViewCreated: $template")
        }

    }

    private suspend fun  getJson() = withContext(Dispatchers.IO) {
        val jsonString = Utils.readJson(requireContext())
//        binding.textView.text = jsonString
//        Log.d("LabsFragmentJson", "jsonString: $jsonString")


        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<ModelAdTemplate> = moshi.adapter(ModelAdTemplate::class.java)
        return@withContext jsonAdapter.fromJson(jsonString)

    }

    private suspend fun bindData() = withContext(Dispatchers.Main){
       mTemplateBinder?.bindBackView(requireContext(), vBinding.labsTemplateFragmentCl)
    }
}