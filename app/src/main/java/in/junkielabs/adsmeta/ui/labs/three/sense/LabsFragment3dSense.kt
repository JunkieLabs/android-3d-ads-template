package `in`.junkielabs.adsmeta.ui.labs.three.sense

import `in`.junkielabs.adsmeta.databinding.Labs3dsenseFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import java.util.*

/**
 * Created by Niraj on 30-11-2022.
 */
class LabsFragment3dSense  : FragmentBase(true), SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private lateinit var vBinding: Labs3dsenseFragmentBinding

    private val mViewModel: Labs3dSenseViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            Labs3dsenseFragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
                lifecycleOwner = this@LabsFragment3dSense.viewLifecycleOwner
            }
        return vBinding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // labs_3d_textview

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.bSenseRotation.observe(viewLifecycleOwner) {
//            var xOffset = it.roll.
            val dm = resources.displayMetrics
            val xOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.roll, dm)
            val yOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.pitch, dm)
            Log.d("LabsFragment3dSense", "setupViewModelObservers: $xOffset ${it.roll}")

//            mViewModel.addOffset(xOffset*10)
            var param =  vBinding.labs3dsenseFragmentCard.layoutParams as ConstraintLayout.LayoutParams
            param.leftMargin = - (xOffset*20f).toInt()
            param.topMargin = - (yOffset*20f).toInt()
            vBinding.labs3dsenseFragmentCard.layoutParams =  param


        }

        mViewModel.bMarginLeft.observe(viewLifecycleOwner){
            Log.d("LabsFragment3dSense", "bMarginLeft: $it")


        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList = mSensorManager?.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR)?.distinctBy { it.type }?.toList()

        if(sensorList!=null && sensorList.isNotEmpty()) {
            registerSensor()


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterSensor()
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


}