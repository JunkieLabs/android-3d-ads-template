package `in`.junkielabs.adsmeta.ui.pages.about

import `in`.junkielabs.adsmeta.databinding.AboutFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AboutFragment : FragmentBase(true) {
    private lateinit var vBinding: AboutFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        vBinding = AboutFragmentBinding.inflate(layoutInflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.buttonGithub.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/JunkieLabs/android-meta-ads"))
            startActivity(intent)
        }
        vBinding.buttonJunkiesLabs.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://junkielabs.in"))
            startActivity(intent)
        }
    }
}