package za.co.dvt.pokeverse.common.extensions

fun String.toTitleCase(): String {
    if (this.isBlank()) {
        return this
    }
    return this[0].uppercase() + this.substring(1)
}