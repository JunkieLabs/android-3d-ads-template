package `in`.junkielabs.adsmeta.domain.ads.usecase

import `in`.junkielabs.adsmeta.domain.ads.repository.AdsRepository
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import javax.inject.Inject

/**
 * Created by Niraj on 21-12-2022.
 */
internal class GetAdsUseCase @Inject constructor(private var adsRepository: AdsRepository) {
suspend operator fun invoke(): DomainResult<ModelAdList> = adsRepository.getAll()

}