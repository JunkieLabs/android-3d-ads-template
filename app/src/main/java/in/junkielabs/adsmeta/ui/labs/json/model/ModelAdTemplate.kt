package `in`.junkielabs.adsmeta.ui.labs.json.model

data class ModelAdTemplate(
    val backgrounds: List<Model2DNode>,
    val contentObjects: List<Model2DNode>,
    val threeDObjects: List<Any>
)