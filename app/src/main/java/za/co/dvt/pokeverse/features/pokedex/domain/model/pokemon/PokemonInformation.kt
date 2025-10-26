package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

data class PokemonInformation(
    val frontDefaultSprite: String = "",
    val isBattleOnly: Boolean = false,
    val pokemonAbilityList: List<PokemonAbility> = emptyList(),
    val stats: List<Stats> = emptyList()
)