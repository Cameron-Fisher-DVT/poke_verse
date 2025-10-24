package za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource

import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse

interface PokedexRemoteDataSource {
    suspend fun fetchPokemonListResponse(): ApiResponse<PokemonListResponse>
}