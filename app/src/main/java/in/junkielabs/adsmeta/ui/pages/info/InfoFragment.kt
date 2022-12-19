package `in`.junkielabs.adsmeta.ui.pages.info

import `in`.junkielabs.adsmeta.databinding.FragmentInfoBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InfoFragment : FragmentBase(true) {
    private lateinit var vBinding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        vBinding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return vBinding.root
    }
}