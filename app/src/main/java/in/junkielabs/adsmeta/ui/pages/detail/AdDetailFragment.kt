package `in`.junkielabs.adsmeta.ui.pages.detail

import `in`.junkielabs.adsmeta.databinding.DetailFragmentBinding
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataObserver
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Niraj on 29-12-2022.
 */
@AndroidEntryPoint
class AdDetailFragment : FragmentBase(true) {

    private lateinit var vBinding: DetailFragmentBinding

    private val mViewModel by viewModels<AdDetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        vBinding = DetailFragmentBinding.inflate(layoutInflater, container, false).apply {
            bViewModel = mViewModel
//            lifecycleOwner = viewLifecycleOwner
        }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.lifecycleOwner = this.viewLifecycleOwner




    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()

    }
}