package za.co.dvt.pokeverse.features.pokedex.data.local.dataSource

import za.co.dvt.pokeverse.R
import za.co.dvt.pokeverse.common.data.local.common.DatabaseResponse
import za.co.dvt.pokeverse.common.data.local.database.DatabaseConstants
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonDao
import za.co.dvt.pokeverse.common.data.local.database.dao.SearchHistoryDao
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonWithAbilities
import za.co.dvt.pokeverse.common.data.local.model.search.SearchHistoryEntity
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManager

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val searchHistoryDao: SearchHistoryDao,
    private val resourceManager: ResourceManager
) : PokemonLocalDataSource {
    override suspend fun fetchAllPokemonWithAbilities(offset: Int, limit: Int): DatabaseResponse<List<PokemonWithAbilities>> {
        val pokemonWithAbilitiesList = pokemonDao.fetchAllPokemonWithAbilities(offset, limit)
        return if (pokemonWithAbilitiesList.isNotEmpty()) {
            DatabaseResponse.Success(pokemonWithAbilitiesList)
        } else {
            DatabaseResponse.Error(resourceManager.getString(R.string.pokedex_no_entries_found))
        }
    }

    override suspend fun savePokemonList(pokemonEntityList: List<PokemonEntity>): DatabaseResponse<String> {
        val result = pokemonDao.insert(pokemonEntityList)
        return if (result.any { it == DatabaseConstants.INVALID_OPERATION }) {
            DatabaseResponse.Error(resourceManager.getString(R.string.pokedex_items_not_saved))
        } else {
            DatabaseResponse.Success(resourceManager.getString(R.string.pokedex_successfully_saved))
        }
    }

    override suspend fun updatePokemon(pokemonEntity: PokemonEntity): DatabaseResponse<PokemonEntity> {
        val result = pokemonDao.update(pokemonEntity)
        return if (result == DatabaseConstants.INVALID_OPERATION.toInt() ) {
            DatabaseResponse.Error(resourceManager.getString(R.string.pokedex_item_not_updated))
        } else {
            DatabaseResponse.Success(pokemonEntity)
        }
    }

    override suspend fun saveSearchHistory(searchHistoryEntity: SearchHistoryEntity): DatabaseResponse<SearchHistoryEntity> {
        val result = searchHistoryDao.insert(searchHistoryEntity)
        return if (result == DatabaseConstants.INVALID_OPERATION) {
            DatabaseResponse.Error(resourceManager.getString(R.string.pokedex_items_not_saved))
        } else {
            DatabaseResponse.Success(searchHistoryEntity)
        }
    }

    override suspend fun fetchSearchHistoryList(): DatabaseResponse<List<SearchHistoryEntity>> {
        val result = searchHistoryDao.fetchLastTenSearchHistoryQueries()
        return if (result.isNotEmpty()) {
            DatabaseResponse.Success(result)
        } else {
            DatabaseResponse.Error(resourceManager.getString(R.string.pokedex_no_search_history_available))
        }
    }
}