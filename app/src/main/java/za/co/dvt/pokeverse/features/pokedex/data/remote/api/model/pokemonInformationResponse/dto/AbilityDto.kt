package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto

import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    val name: String = "",
    val url: String = ""
)