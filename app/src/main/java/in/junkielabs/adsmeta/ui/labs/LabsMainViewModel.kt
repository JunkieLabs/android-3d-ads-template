package `in`.junkielabs.adsmeta.ui.labs

import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Niraj on 20-11-2022.
 */
class LabsMainViewModel: ViewModelBase() {


    private val _mEventNavigate = MutableLiveData<LiveDataEvent<Int>>()
    val mEventNavigate: MutableLiveData<LiveDataEvent<Int>> = _mEventNavigate


    fun navigateToSample1(){
//        Log.i("LabsMainViewModel", "navigateToSample1")

        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.SAMPLE_1))
    }

    fun navigateToDefault(){
//        Log.i("LabsMainViewModel", "navigateToDefault")

        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.DEFAULT))

    }

    fun navigateToJson(){
        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.JSON))
    }


}