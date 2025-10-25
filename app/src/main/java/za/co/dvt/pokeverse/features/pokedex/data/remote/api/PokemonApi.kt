package za.co.dvt.pokeverse.features.pokedex.data.remote.api

import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse

interface PokemonApi {
    suspend fun fetchPokemonListResponse(): NetworkResponse<PokemonListResponse>
}