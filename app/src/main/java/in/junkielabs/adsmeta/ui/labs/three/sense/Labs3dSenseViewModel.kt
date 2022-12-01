package `in`.junkielabs.adsmeta.ui.labs.three.sense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Niraj on 30-11-2022.
 */
class Labs3dSenseViewModel: ViewModel() {

    var bSenseRotation = MutableLiveData<Labs3dSenseRotation>()
    var bMarginLeft = MutableLiveData<Float>()


    //remember { mutableStateOf<SensorData?>(null) }

    fun addData(sensorData: Labs3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

    fun addOffset(offsetX: Float){

        bMarginLeft.postValue(offsetX)
    }

}