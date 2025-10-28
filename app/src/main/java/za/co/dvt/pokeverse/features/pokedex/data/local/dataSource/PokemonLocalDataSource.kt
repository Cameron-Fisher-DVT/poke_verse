package za.co.dvt.pokeverse.features.pokedex.data.local.dataSource

import za.co.dvt.pokeverse.common.data.local.common.DatabaseResponse
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonWithAbilities
import za.co.dvt.pokeverse.common.data.local.model.search.SearchHistoryEntity

interface PokemonLocalDataSource {
    suspend fun fetchAllPokemonWithAbilities(offset: Int, limit: Int): DatabaseResponse<List<PokemonWithAbilities>>
    suspend fun savePokemonList(pokemonEntityList: List<PokemonEntity>): DatabaseResponse<String>
    suspend fun updatePokemon(pokemonEntity: PokemonEntity): DatabaseResponse<PokemonEntity>
    suspend fun saveSearchHistory(searchHistoryEntity: SearchHistoryEntity): DatabaseResponse<SearchHistoryEntity>
    suspend fun fetchSearchHistoryList(): DatabaseResponse<List<SearchHistoryEntity>>

}