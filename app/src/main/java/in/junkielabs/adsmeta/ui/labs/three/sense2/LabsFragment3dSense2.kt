package `in`.junkielabs.adsmeta.ui.labs.three.sense2

import `in`.junkielabs.adsmeta.databinding.Labs3dSense2FragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.three.sense.Labs3dSenseRotation
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.BaseTransformableNode
import com.google.ar.sceneform.ux.SelectionVisualizer
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem

/**
 * Created by Niraj on 08-12-2022.
 */
class LabsFragment3dSense2 : FragmentBase(true), SensorEventListener {
    private lateinit var mTransformationSystem: TransformationSystem
    private var mNode: Node? = null
    private var mScene: Scene? = null

    private var mSensorManager: SensorManager? = null
    private lateinit var vBinding: Labs3dSense2FragmentBinding

    private var mSensorCounter = 0

    private val mViewModel: Labs3dSense2ViewModel by viewModels()

    private var mTransformableNode: TransformableNode? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            Labs3dSense2FragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
                lifecycleOwner = this@LabsFragment3dSense2.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // labs_3d_textview

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.bSenseRotation.observe(viewLifecycleOwner) {
//            var xOffset = it.roll.
//            val dm = resources.displayMetrics
//            val xOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.roll, dm)
//            val yOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.pitch, dm)
            mSensorCounter++;
            if (mSensorCounter.and(0xf) == 0) {
                Log.d("LabsFragment3dSense2", "setupViewModelObservers: ${it.roll} ${it.pitch}")
                senseRotate(it)
            }

//            mViewModel.addOffset(xOffset*10)
/*            var param =  vBinding.labs3dsenseFragmentCard.layoutParams as ConstraintLayout.LayoutParams
            param.leftMargin = - (xOffset*20f).toInt()
            param.topMargin = - (yOffset*20f).toInt()
            vBinding.labs3dsenseFragmentCard.layoutParams =  param*/


        }

        mViewModel.bMarginLeft.observe(viewLifecycleOwner) {
            Log.d("LabsFragment3dSense", "bMarginLeft: $it")


        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList =
            mSensorManager?.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR)?.distinctBy { it.type }
                ?.toList()

        if (sensorList != null && sensorList.isNotEmpty()) {
            registerSensor()
        }

        mScene = vBinding.labs3dSense2FragmentScene.scene

        vBinding.labs3dSense2FragmentScene.setTransparent(true)
        setupTransformation()

    }

    override fun onResume() {
        super.onResume()
        try {
            vBinding.labs3dSense2FragmentScene.resume()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        vBinding.labs3dSense2FragmentScene.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterSensor()
    }

    private fun senseRotate(it: Labs3dSenseRotation?) {
        // TODO("Not yet implemented")
        var angle = mTransformableNode?.localRotation
        // var sd;
        // Quaternion.

    }

    private fun unregisterSensor() {
        if (mSensorManager == null) return
        mSensorManager?.unregisterListener(
            this,
            mSensorManager!!.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)
        )
    }

    private fun registerSensor() {
        if (mSensorManager == null) return
        mSensorManager?.registerListener(
            this,
            mSensorManager!!.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
//            Log.v("LabsFragment3dSense","Sensor log: ${event.values.size} ${Arrays.toString(event.values)}")
            var r = FloatArray(9)
            var i = FloatArray(9)
            var orientation = FloatArray(3)
            SensorManager.getRotationMatrixFromVector(r, event.values)
            SensorManager.getOrientation(r, orientation)
            mViewModel.addData(Labs3dSenseRotation(orientation[2], orientation[1]))
//            Log.v("LabsFragment3dSense","rotation log:  ${Arrays.toString(orientation)}, ${Arrays.toString(r)}")

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    /* *******************************************************************************
     *                                      render
     */

    private fun setupTransformation() {
        mTransformationSystem = TransformationSystem(resources.displayMetrics, object :
            SelectionVisualizer {
            override fun applySelectionVisual(node: BaseTransformableNode?) {}

            override fun removeSelectionVisual(node: BaseTransformableNode?) {}
        })

        vBinding.labs3dSense2FragmentScene.scene.addOnPeekTouchListener { hitTestResult, motionEvent ->
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

        vBinding.labs3dSense2FragmentScene.scene.camera.localPosition = Vector3(0f, 0f, 3f)
    }


    private fun render(uri: Uri) {
        ModelRenderable.builder()
            .setSource(requireContext(), uri)
            .setIsFilamentGltf(true)
            .setAsyncLoadEnabled(true)
            .build()
            .thenAccept {
//                it.setanimate(true).start()
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

            mTransformableNode = TransformableNode(mTransformationSystem).apply {
                rotationController.isEnabled = true
                scaleController.isEnabled = false
                translationController.isEnabled = false
                parent = vBinding.labs3dSense2FragmentScene.scene
                localPosition = Vector3(0f, 0f, 0f)
                localScale = Vector3(0.7f, 0.7f, 0.7f)

                localRotation = Quaternion.axisAngle(Vector3(1f, 0f, 0f), 35f)
//                renderable = it
                setRenderable(it).animate(true).start()

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
            mScene?.addChild(mTransformableNode)
        }
    }
}