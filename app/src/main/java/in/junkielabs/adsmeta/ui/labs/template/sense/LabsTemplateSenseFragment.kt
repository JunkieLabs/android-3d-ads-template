package `in`.junkielabs.adsmeta.ui.labs.template.sense

import `in`.junkielabs.adsmeta.databinding.LabsTemplateFragmentBinding
import `in`.junkielabs.adsmeta.ui.base.FragmentBase
import `in`.junkielabs.adsmeta.ui.labs.json.Utils
import `in`.junkielabs.adsmeta.ui.labs.json.model.ModelAdTemplate
import `in`.junkielabs.adsmeta.domain.sense.entity.Entity3dSenseRotation
import `in`.junkielabs.adsmeta.ui.templates.TemplateBinder
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Niraj on 25-12-2022.
 */
class LabsTemplateSenseFragment  : FragmentBase(true), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private lateinit var vBinding: LabsTemplateFragmentBinding

    private var mTemplateBinder: TemplateBinder? =null

    private val mViewModel: LabsTemplateSenseViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            LabsTemplateFragmentBinding.inflate(inflater, container, false).apply {
//                viewModel = mViewModel
                lifecycleOwner = this@LabsTemplateSenseFragment.viewLifecycleOwner
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        Asset
//        vBinding.labsTemplateFragmentIv.load("file:///android_asset/cap.PNG")
        lifecycleScope.launchWhenCreated {
            var template = getJson()

            mTemplateBinder = template?.let { TemplateBinder(it) }


            bindData()

//            Log.i("LabsTemplateFragment", "onViewCreated: $template")
        }

        mSensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList = mSensorManager?.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR)?.distinctBy { it.type }?.toList()

        if(sensorList!=null && sensorList.isNotEmpty()) {
            registerSensor()


        }

    }

    private suspend fun  getJson() = withContext(Dispatchers.IO) {
        val jsonString = Utils.readJson(requireContext(), "data/ad_noodle.json")
//        binding.textView.text = jsonString
//        Log.d("LabsFragmentJson", "jsonString: $jsonString")


        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<ModelAdTemplate> = moshi.adapter(ModelAdTemplate::class.java)
        return@withContext jsonAdapter.fromJson(jsonString)

    }

    private suspend fun bindData() = withContext(Dispatchers.Main){


        mTemplateBinder?.bindBackView(requireContext(), vBinding.labsTemplateFragmentCl)
        mTemplateBinder?.bindObjectsView(requireContext(), vBinding.labsTemplateFragmentCl)
    }


    override fun setupViewModelObservers() {
        super.setupViewModelObservers()

        mViewModel.bSenseRotation.observe(viewLifecycleOwner) {
//            var xOffset = it.roll.
            val dm = resources.displayMetrics
            val xOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.roll, dm)
            val yOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, it.pitch, dm)
//            Log.d("LabsFragment3dSense", "setupViewModelObservers: $xOffset ${it.roll}")
            mTemplateBinder?.onSense(requireContext(), vBinding.labsTemplateFragmentCl, it)

//            mViewModel.addOffset(xOffset*10)
          /*
          var param =  vBinding.labs3dsenseFragmentCard.layoutParams as ConstraintLayout.LayoutParams
            param.leftMargin = - (xOffset*20f).toInt()
            param.topMargin = - (yOffset*20f).toInt()
            vBinding.labs3dsenseFragmentCard.layoutParams =  param
            */


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
            mViewModel.addData(Entity3dSenseRotation(orientation[2], orientation[1]))
//            Log.v("LabsFragment3dSense","rotation log:  ${Arrays.toString(orientation)}, ${Arrays.toString(r)}")

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}