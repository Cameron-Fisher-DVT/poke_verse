package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.PokemonAbilityDto
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.SpriteDto
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.StatsDto

@Serializable
data class PokemonInformationResponse(
    @SerialName("abilities")
    val pokemonAbilityDtoList: List<PokemonAbilityDto> = emptyList(),
    @SerialName("sprites")
    val spriteDto: SpriteDto = SpriteDto(),
    @SerialName("is_battle_only")
    val isBattleOnly: Boolean = false,
    val stats: List<StatsDto> = emptyList()
)