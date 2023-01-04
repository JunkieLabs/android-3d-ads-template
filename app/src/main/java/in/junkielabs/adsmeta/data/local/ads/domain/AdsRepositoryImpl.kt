package `in`.junkielabs.adsmeta.data.local.ads.domain

import `in`.junkielabs.adsmeta.data.base.LocalResult
import `in`.junkielabs.adsmeta.data.local.ads.LocalAdsSource
import `in`.junkielabs.adsmeta.domain.ads.repository.AdsRepository
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.domain.template.enitity.ModelAdTemplate
import javax.inject.Inject

/**
 * Created by Niraj on 21-12-2022.
 */
class AdsRepositoryImpl @Inject constructor(val source : LocalAdsSource) : AdsRepository {
    override suspend fun getAll(): DomainResult<ModelAdList> {
//        TODO("Not yet implemented")
        return when(val result = source.getAds()){
            is LocalResult.Success ->{
                DomainResult.Success(result.data)

            }
            is LocalResult.Exception ->{
                DomainResult.Failure(result.throwable)

            }
            is LocalResult.Message ->{
                DomainResult.Failure(Throwable("Not item found"))

            }
        }


    }

    override suspend fun getDetail(name: String): DomainResult<ModelAdTemplate> {
        return when(val result = source.getAdByName("data/${name}.json")){
            is LocalResult.Success ->{
                DomainResult.Success(result.data)

            }
            is LocalResult.Exception ->{
                DomainResult.Failure(result.throwable)

            }
            is LocalResult.Message ->{
                DomainResult.Failure(Throwable("Not item found"))

            }
        }
    }


}