package za.co.dvt.pokeverse.common.extensions

fun String.toTitleCase(): String {
    if (this.isBlank()) {
        return this
    }
    return this[0].uppercase() + this.substring(1)
}

fun String.extractIdFromUrl(): String {
    val segments = this.split("/")
    return if (segments.size >= 2) {
        segments[segments.size - 2]
    } else {
        ""
    }
}