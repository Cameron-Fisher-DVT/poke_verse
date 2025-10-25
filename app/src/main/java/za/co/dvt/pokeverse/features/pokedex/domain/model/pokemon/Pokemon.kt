package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

class Pokemon(
    val pokemonId: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val abilityList: List<PokemonAbility> = emptyList()
)