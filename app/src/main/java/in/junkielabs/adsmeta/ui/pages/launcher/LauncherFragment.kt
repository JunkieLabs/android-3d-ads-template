package `in`.junkielabs.adsmeta.ui.pages.launcher

import `in`.junkielabs.adsmeta.databinding.AdListFragmentBinding
import `in`.junkielabs.adsmeta.databinding.LauncherFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LauncherFragment :FragmentBase(true) {

    private lateinit var vBinding: LauncherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding = LauncherFragmentBinding.inflate(layoutInflater,container,false)
        return vBinding.root
    }


}