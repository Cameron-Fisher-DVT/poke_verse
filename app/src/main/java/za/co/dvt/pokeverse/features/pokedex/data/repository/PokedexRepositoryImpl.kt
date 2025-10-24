package za.co.dvt.pokeverse.features.pokedex.data.repository

import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSource
import za.co.dvt.pokeverse.features.pokedex.data.remote.mapper.PokemonMapper
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository

class PokedexRepositoryImpl(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource
) : PokedexRepository {
    override suspend fun fetchPokemonList(): Result<List<Pokemon>> {
        return when (val apiResponse = pokedexRemoteDataSource.fetchPokemonResponseList()) {
            is ApiResponse.Success<PokemonListResponse> -> {
                Result.Success(PokemonMapper.mapToDomainList(apiResponse.data))
            }

            is ApiResponse.Error<PokemonListResponse> -> {
                Result.Error(apiResponse.message)
            }
        }
    }
}