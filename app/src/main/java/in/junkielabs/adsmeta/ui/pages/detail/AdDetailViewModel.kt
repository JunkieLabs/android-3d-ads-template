package `in`.junkielabs.adsmeta.ui.pages.detail

import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import `in`.junkielabs.adsmeta.domain.sense.entity.Entity3dSenseRotation
import `in`.junkielabs.adsmeta.ui.nav.NavManager
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Niraj on 29-12-2022.
 */
@HiltViewModel
internal class AdDetailViewModel @Inject constructor(
    private val navManager: NavManager
): ViewModelBase() {

    var bSenseRotation = MutableLiveData<Entity3dSenseRotation>()

    fun addData(sensorData: Entity3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

    fun navigateToHome(){
        navManager.navigate(AdDetailFragmentDirections.navigateToList())
    }

    fun openDialogBottomSheet(){
        navManager.navigate(AdDetailFragmentDirections.openDialogBottomSheet())
    }
}