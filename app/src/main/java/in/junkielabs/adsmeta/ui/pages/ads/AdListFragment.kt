package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.R
import `in`.junkielabs.adsmeta.databinding.AdListFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataObserver
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

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


        vBinding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_ad_list_action_about -> navigateToAbout()

                else -> false
            }
        }

        mListAdapter = AdListAdapter(){
            Log.d("AdListFragment", "AdListAdapter: $it")
            navigateToDetail(it.key)
        }
        Log.d("AdListFragment", "onCraeted:")


        lifecycleScope.launchWhenCreated {
            val layoutManager = GridLayoutManager(requireContext(), 2)

            vBinding.recyclerViewAdList.layoutManager = layoutManager
            vBinding.recyclerViewAdList.adapter = mListAdapter



            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return mListAdapter.getItemViewType(position)
                }
            }
            mViewModel.getList()?.let {
                mListAdapter.submitList(it)
            }


            /*mViewModel.mListStateParcel?.let {

                Log.d("AdListAdapter", "listStae: ${it.toString()}")
//                layoutManager.onRestoreInstanceState(it)
//                mViewModel.saveListState(null)
            }*/

        }




/*

        lifecycleScope.launchWhenCreated {
            var adList = getJson()
            vBinding.recyclerViewAdList.layoutManager = GridLayoutManager(requireContext(),2)
            vBinding.recyclerViewAdList.adapter = adList?.items?.let { AdListAdapter(it) }
        }
*/


    }

    private fun navigateToAbout(): Boolean {
        findNavController().navigate(AdListFragmentDirections.navigateToAbout())
        return true

    }

    /*override fun onDestroyView() {
        val listState = vBinding.recyclerViewAdList.layoutManager?.onSaveInstanceState()
        listState?.let { mViewModel.saveListState(it) }
        super.onDestroyView()
    }*/

    private fun navigateToDetail(name: String) {
        findNavController().navigate(AdListFragmentDirections.navigateToAdDetail(name))
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()

        mViewModel.mEventAds.observe(viewLifecycleOwner, LiveDataObserver {
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