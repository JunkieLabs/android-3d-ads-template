package `in`.junkielabs.adsmeta.ui.labs.json

import `in`.junkielabs.adsmeta.databinding.LabsJsonFragmentBinding
import `in`.junkielabs.adsmeta.databinding.LabsSample1FragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LabsFragmentJson: FragmentBase(true) {

    private lateinit var vBinding: LabsJsonFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            LabsJsonFragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
//                lifecycleOwner = this@LabsFragmentSample1.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

}