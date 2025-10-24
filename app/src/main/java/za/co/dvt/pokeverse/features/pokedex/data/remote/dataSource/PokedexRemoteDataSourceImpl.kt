package za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource

import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.adapter.PokemonApiAdapter
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse

class PokedexRemoteDataSourceImpl(
    private val pokemonApiAdapter: PokemonApiAdapter
): PokedexRemoteDataSource {
    override suspend fun fetchPokemonResponseList(): ApiResponse<PokemonListResponse> {
        return pokemonApiAdapter.fetchPokemon()
    }
}