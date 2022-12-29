package `in`.junkielabs.adsmeta.ui.pages.detail.dialog

import `in`.junkielabs.adsmeta.R
import `in`.junkielabs.adsmeta.databinding.DetailBottomSheetBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener

/**
 * Created by Niraj on 29-12-2022.
 */

class AdDetailDialog : BottomSheetDialogFragment() {

    private lateinit var vBinding: DetailBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NORMAL,
            com.google.android.material.R.style.ThemeOverlay_Material3_BottomSheetDialog
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding = DetailBottomSheetBinding.inflate(inflater, container, false)
        /*.apply {
            bViewModel = mViewModel
        }*/
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner

        vBinding.addButton.setOnClickListener {
            animateHideCaption()
        }
    }

    private fun animateHideCaption() {
        var animHide = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.hide
        );
        animHide.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                vBinding.detailBottomSheetCaption.visibility = View.GONE
                animateHideFab()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        vBinding.detailBottomSheetCaption.startAnimation(animHide)
    }

    private fun animateHideFab() {
        vBinding.addButton.hide(object : OnVisibilityChangedListener() {
            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                animateShowDone()

            }
        })
    }

    private fun animateShowDone() {
        //detail_bottom_sheet_caption
//        TODO("Not yet implemented")
    }
}