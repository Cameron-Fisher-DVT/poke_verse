package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto

import kotlinx.serialization.SerialName

data class SpriteDto(
    @SerialName("front_default")
    val frontDefault: String = ""
)