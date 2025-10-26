package za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon

data class Pokemon(
    val pokemonId: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val isBattleOnly: Boolean = false,
    val abilityList: List<PokemonAbility> = emptyList(),
    val statsList: List<Stats> = emptyList(),
    val isFavourite: Boolean = false
)

fun Pokemon.description(): String {
    val stringBuilder = StringBuilder()
    this.statsList.forEach {
        stringBuilder.append("${it.stat.name}: ${it.score}")
        stringBuilder.appendLine()
    }
    return stringBuilder.toString()
}