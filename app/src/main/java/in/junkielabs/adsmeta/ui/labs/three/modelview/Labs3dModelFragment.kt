package `in`.junkielabs.adsmeta.ui.labs.three.modelview

import `in`.junkielabs.adsmeta.databinding.Labs3dModelFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import org.the3deer.android_3d_model_engine.camera.CameraController
import org.the3deer.android_3d_model_engine.collision.CollisionController
import org.the3deer.android_3d_model_engine.controller.TouchController
import org.the3deer.android_3d_model_engine.services.SceneLoader
import org.the3deer.android_3d_model_engine.view.ModelSurfaceView
import org.the3deer.util.android.AssetUtils
import org.the3deer.util.android.ContentUtils
import org.the3deer.util.event.EventListener
import java.net.URI
import java.util.*


/**
 * Created by Niraj on 03-12-2022.
 */
class Labs3dModelFragment : FragmentBase(true), EventListener {
    private var mCameraController: CameraController? =null
    private var mCollisionController: CollisionController? =null
    private var mGlView: ModelSurfaceView? =  null
    private var mScene: SceneLoader? = null
    private val mViewModel: Labs3dModelViewModel by viewModels()
    private lateinit var vBinding: Labs3dModelFragmentBinding

    private var immersiveMode = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            Labs3dModelFragmentBinding.inflate(inflater, container, false).apply {
                viewModel = mViewModel
                lifecycleOwner = this@Labs3dModelFragment.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ContentUtils.provideAssets(requireActivity())
//        requireContext().resources.as()

//        requireContext().resources.assets.open()

//Uri.parse("models/MaterialSuite.glb")//"models/Avocado.gltf")



        load()
//        AssetUtils.createChooserDialog()
    }

    fun load(){
       /* Uri
        URI*/

        val backgroundColor = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f)

        val uri = Uri.parse("android://"+requireActivity().packageName +"/assets/" + "models/Avocado.gltf")
        Log.d("Labs3dModelFragment", "onViewCreated: ${uri}")


        URI.create(uri.toString())
        //AssetUtils
        mScene = SceneLoader(requireActivity(), URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), uri.getQuery(), uri.getFragment()), -1)
        mScene?.addListener(this);

        try {
            Log.i("ModelActivity", "Loading GLSurfaceView...");
//           var s =  GLTextureView
            mGlView = ModelSurfaceView(requireActivity(), backgroundColor, this.mScene);
            mGlView?.id = 232
            mGlView?.setSkyBox(-1)

            mGlView!!.addListener(this);

            vBinding.labs3dModelFragmentCl.addView(mGlView);
            val set = ConstraintSet()
            set.clone(vBinding.labs3dModelFragmentCl)
            set.connect(mGlView!!.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 20)
            set.connect(mGlView!!.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 20) // Add top constraint to your view 1
            set.connect(mGlView!!.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 20) // Optional if you want symmetry of view 1 in layout
            set.connect(mGlView!!.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 20) // Optional if you want symmetry of view 1 in layout
            set.applyTo(vBinding.labs3dModelFragmentCl)




            //            scene.setView(glView);
        } catch (e: Exception ) {

            Log.e("ModelActivity", e.message, e);
            Toast.makeText(requireContext(), "Error loading OpenGL view:\n" + e.message,
                Toast.LENGTH_LONG).show();

        }

        /*try {
            Log.i("ModelActivity", "Loading TouchController...")
            touchController = TouchController(requireActivity())
            touchController.addListener(this)
            //touchController.addListener(glView);
        } catch (e: java.lang.Exception) {
            Log.e("ModelActivity", e.message, e)
            Toast.makeText(
                this, """
     Error loading TouchController:
     ${e.message}
     """.trimIndent(), Toast.LENGTH_LONG
            ).show()
        }*/

        if(mGlView == null){
            return
        }
        try {
            Log.i("ModelActivity", "Loading CollisionController...")
            mCollisionController = CollisionController(mGlView, mScene)
            mCollisionController?.addListener(this)
            //touchController.addListener(collisionController);
            //touchController.addListener(scene);
        } catch (e: java.lang.Exception) {
            Log.e("ModelActivity", e.message, e)
            Toast.makeText(
                requireContext(), """
     Error loading CollisionController
     ${e.message}
     """.trimIndent(), Toast.LENGTH_LONG
            ).show()
        }

        try {
            Log.i("ModelActivity", "Loading CameraController...")
            mCameraController = CameraController(mScene!!.getCamera())
            //glView.getModelRenderer().addListener(cameraController);
            //touchController.addListener(cameraController);
        } catch (e: java.lang.Exception) {
            Log.e("ModelActivity", e.message, e)
            Toast.makeText(requireContext(), "Error loading CameraController" + e.message, Toast.LENGTH_LONG)
                .show()
        }


    }

    override fun onEvent(event: EventObject?): Boolean {
//        TODO("Not yet implemented")
        return true
    }
}