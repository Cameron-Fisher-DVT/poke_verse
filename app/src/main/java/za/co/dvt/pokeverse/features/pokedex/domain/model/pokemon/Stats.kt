package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

data class Stats(
    val score: Int,
    val stat: Stat = Stat(name = "")
)