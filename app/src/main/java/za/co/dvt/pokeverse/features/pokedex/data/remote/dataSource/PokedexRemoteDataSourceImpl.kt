package za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource

import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.adapter.PokemonApiAdapter
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.PokemonInformationResponse

class PokedexRemoteDataSourceImpl(
    private val pokemonApiAdapter: PokemonApiAdapter
): PokedexRemoteDataSource {
    override suspend fun fetchPokemonListResponse(): ApiResponse<PokemonListResponse> {
        return when(val networkResponse = pokemonApiAdapter.fetchPokemonListResponse()) {
            is NetworkResponse.HttpError -> {
                ApiResponse.Error(networkResponse.message)
            }
            is NetworkResponse.NetworkError -> {
                ApiResponse.Error(networkResponse.exception.message ?: "")
            }
            is NetworkResponse.Success<PokemonListResponse> -> {
                ApiResponse.Success(networkResponse.data)
            }
        }
    }

    override suspend fun fetchPokemonInformationResponse(pokemonId: String): ApiResponse<PokemonInformationResponse> {
        return when(val networkResponse = pokemonApiAdapter.fetchPokemonInformationResponse(pokemonId)) {
            is NetworkResponse.HttpError -> {
                ApiResponse.Error(networkResponse.message)
            }
            is NetworkResponse.NetworkError -> {
                ApiResponse.Error(networkResponse.exception.message ?: "")
            }
            is NetworkResponse.Success<PokemonInformationResponse> -> {
                ApiResponse.Success(networkResponse.data)
            }
        }
    }
}