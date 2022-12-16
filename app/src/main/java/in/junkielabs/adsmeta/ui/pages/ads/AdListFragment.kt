package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.databinding.AdListFragmentBinding
import `in`.junkielabs.adsmeta.databinding.LauncherFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.json.Utils
import `in`.junkielabs.adsmeta.ui.labs.json.model.Model2DNode
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.AdList
import `in`.junkielabs.adsmeta.ui.labs.json.model.adModel.Item
import `in`.junkielabs.adsmeta.ui.templates.TemplateBinder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdListFragment : FragmentBase(true) {
    private lateinit var vBinding: AdListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        vBinding = AdListFragmentBinding.inflate(layoutInflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenCreated {
            var adList = getJson()
            vBinding.recyclerViewAdList.layoutManager = GridLayoutManager(requireContext(),2)
            vBinding.recyclerViewAdList.adapter = adList?.items?.let { AdListAdapter(it) }
        }


    }
    private suspend fun  getJson() = withContext(Dispatchers.IO) {
        val jsonString = Utils.readJson(requireContext(), "data/ads.json")

        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<AdList> = moshi.adapter(AdList::class.java)
        return@withContext jsonAdapter.fromJson(jsonString)

    }
}