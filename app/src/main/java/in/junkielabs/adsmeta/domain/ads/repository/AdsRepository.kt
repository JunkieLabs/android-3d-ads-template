package `in`.junkielabs.adsmeta.domain.ads.repository

import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList

/**
 * Created by Niraj on 21-12-2022.
 */

interface AdsRepository {
   suspend fun getAll(): DomainResult<ModelAdList>

}