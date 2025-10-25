package za.co.dvt.pokeverse.features.pokedex.data.local.dataSource

import za.co.dvt.pokeverse.common.data.local.common.DatabaseResponse
import za.co.dvt.pokeverse.common.data.local.database.DatabaseConstants
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonDao
import za.co.dvt.pokeverse.common.data.local.model.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.PokemonWithAbilities

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {
    override suspend fun fetchAllPokemonWithAbilities(): DatabaseResponse<List<PokemonWithAbilities>> {
        val pokemonWithAbilitiesList = pokemonDao.fetchAllPokemonWithAbilities()
        return if (pokemonWithAbilitiesList.isNotEmpty()) {
            DatabaseResponse.Success(pokemonWithAbilitiesList)
        } else {
            DatabaseResponse.Error("No entries found.")
        }
    }

    override suspend fun savePokemonList(pokemonEntityList: List<PokemonEntity>): DatabaseResponse<String> {
        val result = pokemonDao.insert(pokemonEntityList)
        return if (result.any { it == DatabaseConstants.INVALID_OPERATION }) {
            DatabaseResponse.Error("Items not saved.")
        } else {
            DatabaseResponse.Success("Successfully saved.")
        }
    }
}