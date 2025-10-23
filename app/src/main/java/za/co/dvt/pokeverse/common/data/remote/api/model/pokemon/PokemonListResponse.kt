package za.co.dvt.pokeverse.common.data.remote.api.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import za.co.dvt.pokeverse.common.data.remote.api.model.pokemon.dto.PokemonResultDto

@Serializable
class PokemonListResponse(
    val count: Int = 0,
    val next: String = "",
    val previous: String? = null,
    @SerialName("results")
    val pokemonResultDtoList: List<PokemonResultDto>
)