package `in`.junkielabs.adsmeta.ui.pages.detail

import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import `in`.junkielabs.adsmeta.ui.labs.three.sense.Labs3dSenseRotation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Niraj on 29-12-2022.
 */
@HiltViewModel
internal class AdDetailViewModel @Inject constructor(): ViewModelBase() {

    var bSenseRotation = MutableLiveData<Labs3dSenseRotation>()

    fun addData(sensorData: Labs3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }
}