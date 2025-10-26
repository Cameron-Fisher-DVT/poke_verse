package za.co.dvt.pokeverse.features.pokedex.data.repository

import za.co.dvt.pokeverse.common.data.local.common.DatabaseResponse
import za.co.dvt.pokeverse.common.data.local.mapper.LocalPokemonMapper
import za.co.dvt.pokeverse.common.data.local.model.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.PokemonWithAbilities
import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.data.local.dataSource.PokemonLocalDataSource
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.PokemonInformationResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.dataSource.PokedexRemoteDataSource
import za.co.dvt.pokeverse.features.pokedex.data.remote.mapper.RemotePokemonMapper
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonInformation
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository

class PokedexRepositoryImpl(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokedexRepository {
    override suspend fun fetchPokemonList(): Result<List<Pokemon>> {
        return when (val databaseResponse = pokemonLocalDataSource.fetchAllPokemonWithAbilities()) {
            is DatabaseResponse.Error<List<PokemonWithAbilities>> -> {
                return when (val apiResponse = pokedexRemoteDataSource.fetchPokemonListResponse()) {
                    is ApiResponse.Success<PokemonListResponse> -> {
                        Result.Success(RemotePokemonMapper.mapToPokemonList(apiResponse.data))
                    }

                    is ApiResponse.Error<PokemonListResponse> -> {
                        Result.Error(apiResponse.message)
                    }
                }
            }

            is DatabaseResponse.Success<List<PokemonWithAbilities>> -> {
                Result.Success(LocalPokemonMapper.mapToPokemonList(databaseResponse.data))
            }
        }
    }

    override suspend fun savePokemonList(pokemonList: List<Pokemon>): Result<String> {
        return when (val databaseResponse = pokemonLocalDataSource.savePokemonList(LocalPokemonMapper.mapToPokemonEntityList(pokemonList))) {
            is DatabaseResponse.Error<String> -> {
                Result.Error(databaseResponse.message)
            }

            is DatabaseResponse.Success<String> -> {
                Result.Success(databaseResponse.data)
            }
        }
    }

    override suspend fun fetchPokemonInformation(pokemonId: String): Result<PokemonInformation> {
        return when (val apiResponse = pokedexRemoteDataSource.fetchPokemonInformationResponse(pokemonId)) {
            is ApiResponse.Success<PokemonInformationResponse> -> {
                Result.Success(RemotePokemonMapper.mapToPokemonInformation(apiResponse.data))
            }

            is ApiResponse.Error<PokemonInformationResponse> -> {
                Result.Error(apiResponse.message)
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon): Result<Pokemon> {
        return when (val databaseResponse = pokemonLocalDataSource.updatePokemon(LocalPokemonMapper.mapToPokemonEntity(pokemon))) {
            is DatabaseResponse.Error<PokemonEntity> -> {
                Result.Error(databaseResponse.message)
            }

            is DatabaseResponse.Success<PokemonEntity> -> {
                Result.Success(LocalPokemonMapper.mapToPokemon(databaseResponse.data))
            }
        }
    }
}