package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilityDto(
    @SerialName("ability")
    val abilityDto: AbilityDto = AbilityDto()
)