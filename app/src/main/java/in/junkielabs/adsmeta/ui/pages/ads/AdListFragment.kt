package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.databinding.AdListFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.json.Utils
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class AdListFragment : FragmentBase(true) {
    private lateinit var vBinding: AdListFragmentBinding

    private val mViewModel by viewModels<AdListViewModel>()

    private lateinit var mListAdapter: AdListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        vBinding = AdListFragmentBinding.inflate(layoutInflater, container, false).apply {
            bViewModel = mViewModel
//            lifecycleOwner = viewLifecycleOwner
        }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.lifecycleOwner = this.viewLifecycleOwner

        mListAdapter = AdListAdapter()
        lifecycleScope.launchWhenCreated {
            vBinding.recyclerViewAdList.layoutManager = GridLayoutManager(requireContext(),2)
            vBinding.recyclerViewAdList.adapter =  mListAdapter
        }
/*

        lifecycleScope.launchWhenCreated {
            var adList = getJson()
            vBinding.recyclerViewAdList.layoutManager = GridLayoutManager(requireContext(),2)
            vBinding.recyclerViewAdList.adapter = adList?.items?.let { AdListAdapter(it) }
        }
*/


    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()

        mViewModel.mEventAds.observe(viewLifecycleOwner, LiveDataObserver{
            mListAdapter.submitList(it)

        })
    }


    /*private suspend fun  getJson() = withContext(Dispatchers.IO) {
        val jsonString = Utils.readJson(requireContext(), "data/ads.json")

        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<ModelAdList> = moshi.adapter(ModelAdList::class.java)
        return@withContext jsonAdapter.fromJson(jsonString)

    }*/
}