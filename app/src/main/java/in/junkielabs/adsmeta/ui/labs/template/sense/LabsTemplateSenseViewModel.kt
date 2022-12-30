package `in`.junkielabs.adsmeta.ui.labs.template.sense

import `in`.junkielabs.adsmeta.domain.sense.entity.Entity3dSenseRotation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Niraj on 25-12-2022.
 */
class LabsTemplateSenseViewModel: ViewModel() {

    var bSenseRotation = MutableLiveData<Entity3dSenseRotation>()

    fun addData(sensorData: Entity3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

}