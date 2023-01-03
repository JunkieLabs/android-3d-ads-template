package `in`.junkielabs.adsmeta.ui.labs

import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Niraj on 20-11-2022.
 */
@HiltViewModel
class LabsMainViewModel @Inject constructor(): ViewModelBase() {


    private val _mEventNavigate = MutableLiveData<LiveDataEvent<Int>>()
    val mEventNavigate: MutableLiveData<LiveDataEvent<Int>> = _mEventNavigate



    fun navigateToApp(){
        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.NAV_APP))

    }


    fun navigateTo3d(){
        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.THREE_D))
    }

    fun navigateTo3dSense2(){
        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.THREE_D_SENSE2))

    }

    fun navigateTo3dModel(){
        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.THREE_D_MODEL))

    }


}