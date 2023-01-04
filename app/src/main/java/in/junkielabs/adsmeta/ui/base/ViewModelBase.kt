package `in`.junkielabs.adsmeta.ui.base

import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Niraj on 20-11-2022.
 */
abstract class ViewModelBase : ViewModel() {
    private val mSnackBarText = MutableLiveData<LiveDataEvent<String>>()
    val snackBarText: LiveData<LiveDataEvent<String>> = mSnackBarText
}