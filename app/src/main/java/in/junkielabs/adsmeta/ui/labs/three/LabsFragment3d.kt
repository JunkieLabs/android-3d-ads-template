package `in`.junkielabs.adsmeta.ui.labs.three

import `in`.junkielabs.adsmeta.databinding.Labs3dFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.BaseTransformableNode
import com.google.ar.sceneform.ux.SelectionVisualizer
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem
import com.gorisse.thomas.sceneform.environment


/*import io.github.sceneview.math.Position
import io.github.sceneview.node.ModelNode*/

//import io.github.sceneview.loaders.loadHdrIndirectLight

/**
 * Created by Niraj on 28-11-2022.
 */
class LabsFragment3d : FragmentBase(true) {


    private lateinit var mTransformationSystem: TransformationSystem
    private var mNode: Node? = null
    private var mScene: Scene? = null
    private lateinit var vBinding: Labs3dFragmentBinding

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mScene = vBinding.labs3dFragmentScene.scene
        vBinding.labs3dFragmentScene.setTransparent(true)
        mTransformationSystem = TransformationSystem(resources.displayMetrics, object :
            SelectionVisualizer {
            override fun applySelectionVisual(node: BaseTransformableNode?) {}

            override fun removeSelectionVisual(node: BaseTransformableNode?) {}
        })

        vBinding.labs3dFragmentScene.scene.addOnPeekTouchListener { hitTestResult, motionEvent ->
//            Log.d("LabsFragment3d", "addOnPeekTouchListener: $hitTestResult :: $motionEvent")
            mTransformationSystem.onTouch(hitTestResult, motionEvent)
        }
        /*val transformableNode = TransformableNode(transformationSystem).apply {
            rotationController.isEnabled = true
            scaleController.isEnabled = false
            translationController.isEnabled = false
            setParent(vBinding.labs3dFragmentScene.scene)
            //localPosition = Vector3(0f, 0f, -1f)
            localScale = Vector3(0.7f, 0.7f, 0.7f)
            renderable = modelRenderable // Build using CompletableFuture
            select()
        }*/
//        vBinding.labs3dFragmentScene.environment?.skybox.
        render(Uri.parse("models/dragon.glb"))

        vBinding.labs3dFragmentScene.scene.camera.localPosition = Vector3(0f, 0f, 3f)
        //CameraManipulator
//        CameraManipulator
    }

    override fun onResume() {
        super.onResume()
        try {
            vBinding.labs3dFragmentScene.resume()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        vBinding.labs3dFragmentScene.pause()
    }

    private fun render(uri: Uri) {
        ModelRenderable.builder()
            .setSource(requireContext(), uri)
            .setIsFilamentGltf(true)
            .setAsyncLoadEnabled(true)
            .build()
            .thenAccept {
                addNode(it)
//                mScene?.camera.tra
            }
            .exceptionally {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                return@exceptionally null
            }

    }

    private fun addNode(model: ModelRenderable?) {
        model?.let {
            /*mNode = Node().apply {
                renderable = it
                //setParent(scene)
//                worldPosition = Vector3(0f, -0f, -4f)
                localScale = Vector3(1f, 1f, 1f)


                localRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 35f)
                localPosition = Vector3(0f, 0f, -1.0f)
//                renderable = it
                isSelectable = true
                isEnabled = true



            }*/

            var transformableNode = TransformableNode(mTransformationSystem).apply {
                rotationController.isEnabled = true
                scaleController.isEnabled = false
                translationController.isEnabled = false
                parent = vBinding.labs3dFragmentScene.scene
                localPosition = Vector3(0f, 0f, 0f)
                localScale = Vector3(0.7f, 0.7f, 0.7f)

                localRotation = Quaternion.axisAngle(Vector3(1f, 0f, 0f), 35f)
                renderable = it
//                renderable = modelRenderable // Build using CompletableFuture
//                parent = mNode
                select()
            }
//            TransformableNode(mScene.view.)

/*            val transformableNode = TransformableNode()
            transformableNode.setParent(mNode)
            transformableNode.localRotation = Quaternion.axisAngle(Vector3(1f, 0f, 0f), 0f)
            transformableNode.renderable = renderable
            transformableNode.select()*/

            // mNode
            mScene?.addChild(transformableNode)
        }
    }

    fun attachlistener(){

    }


}


/*

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

*/