package za.co.dvt.pokeverse.common.data.remote.api

import za.co.dvt.pokeverse.common.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse

interface PokemonApi {
    suspend fun fetchPokemon(): ApiResponse<PokemonListResponse>
}