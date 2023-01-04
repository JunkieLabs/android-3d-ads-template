package `in`.junkielabs.adsmeta.domain.ads.usecase

import `in`.junkielabs.adsmeta.domain.ads.repository.AdsRepository
import `in`.junkielabs.adsmeta.domain.base.result.DomainResult
import `in`.junkielabs.adsmeta.domain.ads.models.ModelAdList
import `in`.junkielabs.adsmeta.domain.template.enitity.ModelAdTemplate
import javax.inject.Inject

/**
 * Created by Niraj on 21-12-2022.
 */
internal class GetAdDetailUseCase @Inject constructor(private var adsRepository: AdsRepository) {
suspend operator fun invoke(name: String): DomainResult<ModelAdTemplate> = adsRepository.getDetail(name)

}