package za.co.dvt.pokeverse.features.pokedex.data.remote.api

import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.PokemonInformationResponse

interface PokemonApi {
    suspend fun fetchPokemonListResponse(offset: Int, limit: Int): NetworkResponse<PokemonListResponse>
    suspend fun fetchPokemonInformationResponse(pokemonId: String): NetworkResponse<PokemonInformationResponse>
}