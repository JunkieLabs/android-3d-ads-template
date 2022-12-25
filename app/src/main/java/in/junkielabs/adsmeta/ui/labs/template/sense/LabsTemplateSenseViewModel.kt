package `in`.junkielabs.adsmeta.ui.labs.template.sense

import `in`.junkielabs.adsmeta.ui.labs.three.sense.Labs3dSenseRotation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Niraj on 25-12-2022.
 */
class LabsTemplateSenseViewModel: ViewModel() {

    var bSenseRotation = MutableLiveData<Labs3dSenseRotation>()

    fun addData(sensorData: Labs3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

}