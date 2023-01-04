package `in`.junkielabs.adsmeta.ui.labs.three.sense2

import `in`.junkielabs.adsmeta.domain.sense.entity.Entity3dSenseRotation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Niraj on 08-12-2022.
 */
class Labs3dSense2ViewModel: ViewModel() {
    var bSenseRotation = MutableLiveData<Entity3dSenseRotation>()
    var bMarginLeft = MutableLiveData<Float>()


    //remember { mutableStateOf<SensorData?>(null) }

    fun addData(sensorData: Entity3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

    fun addOffset(offsetX: Float){

        bMarginLeft.postValue(offsetX)
    }
}