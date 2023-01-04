package `in`.junkielabs.adsmeta.domain.ads.repository

import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import `in`.junkielabs.adsmeta.domain.template.enitity.ModelAdTemplate

/**
 * Created by Niraj on 21-12-2022.
 */

interface AdsRepository {
   suspend fun getAll(): DomainResult<ModelAdList>
   suspend fun getDetail(name: String): DomainResult<ModelAdTemplate>

}