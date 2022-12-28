package `in`.junkielabs.adsmeta.ui.pages.launcher

import `in`.junkielabs.adsmeta.R
import `in`.junkielabs.adsmeta.databinding.AdListFragmentBinding
import `in`.junkielabs.adsmeta.databinding.LauncherFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.widget.drawable.TileDrawable
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val d = ContextCompat.getDrawable(requireContext(), R.drawable.dr_launcher_tile)
        vBinding.launcherIllustrationBack.setImageDrawable( d?.let { TileDrawable(it, Shader.TileMode.REPEAT) })

        /*vBinding.launcherIllustrationCl.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
    }


}