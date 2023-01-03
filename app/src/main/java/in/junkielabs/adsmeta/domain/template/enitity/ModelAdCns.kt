package `in`.junkielabs.adsmeta.domain.template.enitity

data class ModelAdCns(
    val imageSrc: String?,
    val mimeType: String?,
    val scaleType: String? = "contain",
    val source: String?,
    val thumbnailSrc: String?
)
