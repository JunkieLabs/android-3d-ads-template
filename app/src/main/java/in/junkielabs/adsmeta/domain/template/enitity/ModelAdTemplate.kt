package `in`.junkielabs.adsmeta.domain.template.enitity

import `in`.junkielabs.adsmeta.domain.template.enitity.Model2DNode

data class ModelAdTemplate(
    val backgrounds: List<Model2DNode>,
    val contentObjects: List<Model2DNode>,
    val threeDObjects: List<Any>
)