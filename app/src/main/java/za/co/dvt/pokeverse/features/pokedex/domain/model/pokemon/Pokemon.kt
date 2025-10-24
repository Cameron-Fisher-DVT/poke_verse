package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

class Pokemon(
    val name: String = "",
    val url: String = "",
    val imageUrl: String = "",
    val abilityList: List<PokemonAbility> = emptyList()
)