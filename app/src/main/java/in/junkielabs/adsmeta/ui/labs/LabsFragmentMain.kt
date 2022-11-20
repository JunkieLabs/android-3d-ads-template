package `in`.junkielabs.adsmeta.ui.labs

import `in`.junkielabs.adsmeta.databinding.LabsMainFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

/**
 * Created by Niraj on 20-11-2022.
 */
class LabsFragmentMain: FragmentBase(true) {


    private val viewModel: LabsMainViewModel by viewModels()
    private lateinit var vBinding: LabsMainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            LabsMainFragmentBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = this@LabsFragmentMain.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
    }


//    onCreateV


}