package `in`.junkielabs.adsmeta.ui.pages.ads

import `in`.junkielabs.adsmeta.domain.ads.usecase.GetAdsUseCase
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdItem
import android.os.Parcelable
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
    private var mCurrentList: List<ModelAdItem>? = null
    var mListStateParcel: Parcelable? = null

    fun saveListState(parcel: Parcelable?) {
        mListStateParcel = parcel
    }

    private val _mEventAds = MutableLiveData<LiveDataEvent<List<ModelAdItem>>>()
    val mEventAds: LiveData<LiveDataEvent<List<ModelAdItem>>> = _mEventAds
    val bIsProgress = MutableLiveData(false)
    init {
        initList()
    }

    fun getList(): List<ModelAdItem>? {
        return mCurrentList
    }

    private fun initList() {
        viewModelScope.launch {
            bIsProgress.postValue(true)
            getAdsUseCase().also {

                bIsProgress.postValue(false)
                when(it) {
                    is DomainResult.Success -> {
                        mCurrentList = it.value.items
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