package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsDto(
    @SerialName("base_stat")
    val score: Int = 0,
    @SerialName("stat")
    val statDto: StatDto = StatDto()
)