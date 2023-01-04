package `in`.junkielabs.adsmeta.ui.pages.detail

import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdItem
import `in`.junkielabs.adsmeta.domain.ads.usecase.GetAdDetailUseCase
import `in`.junkielabs.adsmeta.domain.ads.usecase.GetAdsUseCase
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.ui.base.ViewModelBase
import `in`.junkielabs.adsmeta.domain.sense.entity.Entity3dSenseRotation
import `in`.junkielabs.adsmeta.domain.template.enitity.ModelAdTemplate
import `in`.junkielabs.adsmeta.tools.livedata.LiveDataEvent
import `in`.junkielabs.adsmeta.ui.nav.NavManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Niraj on 29-12-2022.
 */
@HiltViewModel
internal class AdDetailViewModel @Inject constructor(
    private val navManager: NavManager,
    private val getAdDetailUseCase: GetAdDetailUseCase
): ViewModelBase() {
    private  var mTemplate: ModelAdTemplate? =null
    val bIsProgress = MutableLiveData(false)

    private val _mEventTemplate = MutableLiveData<LiveDataEvent<ModelAdTemplate>>()
    val mEventTemplate: LiveData<LiveDataEvent<ModelAdTemplate>> = _mEventTemplate
    var bSenseRotation = MutableLiveData<Entity3dSenseRotation>()

    fun initArgs(args: AdDetailFragmentArgs) {
        //getAlbum(args)
        viewModelScope.launch {
            getAdDetailUseCase(args.name).also {

                bIsProgress.postValue(false)
                when(it) {
                    is DomainResult.Success -> {
                        mTemplate = it.value
                        _mEventTemplate.postValue(LiveDataEvent(it.value))
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))
                        Log.d("AdListViewModel", "getList: failure")

                    }
                }

            }
        }
    }

    fun addData(sensorData: Entity3dSenseRotation){
        bSenseRotation.postValue(sensorData)
    }

    fun navigateToHome(){
        navManager.navigate(AdDetailFragmentDirections.navigateToList())
    }

    fun openDialogBottomSheet(){
        navManager.navigate(AdDetailFragmentDirections.openDialogBottomSheet())
    }

    fun openGuide(){
        navManager.navigate(AdDetailFragmentDirections.openGuideDialogBottomSheet())
    }
}