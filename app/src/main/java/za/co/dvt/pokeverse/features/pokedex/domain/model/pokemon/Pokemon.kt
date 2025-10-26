package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

data class Pokemon(
    val pokemonId: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val isBattleOnly: Boolean = false,
    val description: String = "",
    val abilityList: List<PokemonAbility> = emptyList(),
    val statsList: List<Stats> = emptyList()
)