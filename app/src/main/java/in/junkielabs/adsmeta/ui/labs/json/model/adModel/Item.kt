package `in`.junkielabs.adsmeta.ui.labs.json.model.adModel

data class Item(
    val cns: Cns,
    val color: String,
    val spanCount: Int,
    val tags: List<Tag>,
    val title: String
)