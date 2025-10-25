package za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse

import kotlinx.serialization.SerialName
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.dto.SpriteDto
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonAbility

data class PokemonInformationResponse(
    @SerialName("abilities")
    val pokemonAbilityList: List<PokemonAbility> = emptyList(),
    val sprites: SpriteDto = SpriteDto(),
    @SerialName("is_battle_only")
    val isBattleOnly: Boolean = false
)