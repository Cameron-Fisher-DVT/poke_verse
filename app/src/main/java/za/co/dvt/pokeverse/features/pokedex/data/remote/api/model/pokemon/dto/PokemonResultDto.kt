package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResultDto(
    val name: String = "",
    val url: String = ""
)