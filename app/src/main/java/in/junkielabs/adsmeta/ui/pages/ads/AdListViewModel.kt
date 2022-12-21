package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.domain.ads.usecase.GetAdsUseCase
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Niraj on 21-12-2022.
 */
@HiltViewModel
internal class AdListViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase
): ViewModelBase() {


    private val _mEventAds = MutableLiveData<LiveDataEvent<List<ModelAdItem>>>()
    val mEventAds: LiveData<LiveDataEvent<List<ModelAdItem>>> = _mEventAds
    val bIsProgress = MutableLiveData(false)
    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            bIsProgress.postValue(true)
            getAdsUseCase.invoke().also {

                bIsProgress.postValue(false)
                when(it) {
                    is DomainResult.Success -> {
                        _mEventAds.postValue(LiveDataEvent(it.value.items))
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))
                        Log.d("AdListViewModel", "getList: failure")

                    }
                }

            }

        }

    }


}