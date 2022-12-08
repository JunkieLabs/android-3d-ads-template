package `in`.junkielabs.adsmeta.ui.labs.json.model

data class ModelBound(
    val height: ModelBoundDimension?,
    val width: ModelBoundDimension?,
    val positionX: ModelBoundDimension?,
    val positionY: ModelBoundDimension?,
    val alignSelf: List<ModelBoundAlignment> = mutableListOf()
)
