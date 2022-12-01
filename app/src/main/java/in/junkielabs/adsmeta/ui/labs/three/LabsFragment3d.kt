package `in`.junkielabs.adsmeta.ui.labs.three

import `in`.junkielabs.adsmeta.databinding.Labs3dFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.filament.IndirectLight
import com.google.android.filament.utils.HDRLoader
import dev.romainguy.kotlin.math.lookAt
import io.github.sceneview.environment.loadEnvironment
import io.github.sceneview.math.Direction
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.ModelNode
import kotlinx.coroutines.launch

//import io.github.sceneview.loaders.loadHdrIndirectLight

/**
 * Created by Niraj on 28-11-2022.
 */
class LabsFragment3d : FragmentBase(true) {
    private lateinit var vBinding: Labs3dFragmentBinding

    var mModelNode : ModelNode? =null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            Labs3dFragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
                lifecycleOwner = this@LabsFragment3d.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // labs_3d_textview

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()


    }

    private fun navigateTo(it: Int) {
/*        val action = LabsFragmentMain.actionNavigationHomeToShowAllFragment(movieListType)
        findNavController().navigate(action)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        vBinding.labs3dSceneView.li
        lifecycleScope.launchWhenCreated {
           /* vBinding.labs3dSceneView.environment = HDRLoader.loadEnvironment(
                context = requireContext(),
                lifecycle = lifecycle,
                hdrFileLocation = "environments/studio_small_09_2k.hdr",
                specularFilter = true
            )?.apply {
                indirectLight?.intensity = 30_000f
            }*/

            mModelNode = ModelNode(
            );

            mModelNode.apply {
                /*transform(
//                    position = Position(z = -1.5f),
                    rotation = Rotation(x = 30.0f)
                )*/
                /* transform(
                     position = Position(z = -4.0f),
                     rotation = Rotation(x = 15.0f)
                 )*/
//                playAnimation()

            }

            Log.d(
                "LabsFragment3d",
                "world position: ${vBinding.labs3dSceneView.cameraNode.worldPosition}"
            )
//            cameraNode.apply {
//                position
//            }
          /*  vBinding.labs3dSceneView.cameraNode.transform = lookAt(
                eye = modelNode.worldPosition.let {
                    Position(x = it.x, y = it.y, z = it.z - 6.0f)
                },
                target = modelNode.worldPosition,
                up = Direction(y = 1.0f)
            )*/

            mModelNode?.loadModel(
                requireContext(),
                lifecycle,
                "models/MaterialSuite.glb",
                autoAnimate = true,

                centerOrigin = Position(x = 0.0f, y = 0.0f, z = 0.0f),
//                scaleToUnits = 2.0f,
                onError = { exception ->
                    exception.printStackTrace()
                },
            );
            vBinding.labs3dSceneView.addChild(mModelNode!!)

            /* vBinding.labs3dSceneView.cameraNode.smooth(
                 lookAt(
                     eye = modelNode.worldPosition.let {
                         Position(x = it.x, y = it.y, z = it.z - 6.0f)
 //                        Position(x = it.x - 0.4f, y = it.y + 0.4f, z = it.z - 0.6f)
                     },
                     target = modelNode.worldPosition,
                     up = Direction(y = 1.0f)
                 ), speed = 0.7f
             )*/

        }

        /*vBinding.labs3dSceneView.apply {
            setLifecycle(viewLifecycleOwner.lifecycle)
        }*/
//        lifecycleScope.launch {
//          /*  val hdrFile = "environments/studio_small_09_2k.hdr"
//            vBinding.labs3dSceneView.loadHdrIndirectLight(hdrFile, specularFilter = true) {
//                intensity(30_000f)
//            }
//            vBinding.labs3dSceneView.loadHdrSkybox(hdrFile) {
//                intensity(50_000f)
//            }*/
//
//
//          /*  val model = vBinding.labs3dSceneView.modelLoader.loadModel("models/MaterialSuite.glb")!!
//            val modelNode = ModelNode(vBinding.labs3dSceneView, model).apply {
//                transform(
//                    position = Position(z = -4.0f),
//                    rotation = Rotation(x = 15.0f)
//                )
//                scaleToUnitsCube(2.0f)
//                // TODO: Fix centerOrigin
////                centerOrigin(Position(x=-1.0f, y=-1.0f))
//                playAnimation()
//            }*/
///*
//            intensity(30_000f)
//            val indirectLight = IndirectLight.Builder();
//*/
//
////            vBinding.labs3dSceneView.
//            val modelNode = ModelNode(
//
//            );
//
//            modelNode.loadModel( requireContext(),
//                viewLifecycleOwner.lifecycle,
//                "models/MaterialSuite.glb",
//                autoAnimate = true,
//                centerOrigin = Position(x = 0.0f, y = 0.0f, z = 0.0f),
////                scaleToUnits = 2.0f,
//                onError = { exception -> },
//            );
//            modelNode.apply {
//                /*transform(
////                    position = Position(z = -1.5f),
//                    rotation = Rotation(x = 30.0f)
//                )*/
//               /* transform(
//                    position = Position(z = -4.0f),
//                    rotation = Rotation(x = 15.0f)
//                )*/
////                playAnimation()
//
//            }
////            vBinding.labs3dSceneView.model
//            vBinding.labs3dSceneView.addChild(modelNode)
//            vBinding.labs3dSceneView.cameraNode.transform =  lookAt(
//                eye = modelNode.worldPosition.let {
//                    Position(x = it.x, y = it.y , z = it.z + 6.0f)
//                },
//                target = modelNode.worldPosition,
////                up = Direction(y = 1.0f)
//            )
//
////            loadingView.isGone = true
//        }

    }

    override fun onPause() {
        super.onPause()
//        vBinding.labs3dSceneView.onPause(viewLifecycleOwner)
    }

    override fun onResume() {
        super.onResume()
//        vBinding.labs3dSceneView.onResume(viewLifecycleOwner)
    }

    override fun onDestroy() {
        mModelNode?.let { vBinding.labs3dSceneView.removeChild(it) }
        Log.d("LabsFragment3d", "onDestroy: modelnode")
        //onDestroy(viewLifecycleOwner)
        super.onDestroy()


//
    }


}