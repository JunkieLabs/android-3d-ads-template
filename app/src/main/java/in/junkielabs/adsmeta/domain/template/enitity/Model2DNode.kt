package `in`.junkielabs.adsmeta.domain.template.enitity


data class Model2DNode(

    val color: String?,
    val cns: ModelAdCns?,
    val bound: ModelBound?,
    val gradient: ModelGradient?,
    val senseLevel: Int = 0
)
