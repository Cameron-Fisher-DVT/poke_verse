package za.co.dvt.pokeverse.common.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import za.co.dvt.pokeverse.common.data.local.model.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.PokemonWithAbilities

@Dao
interface PokemonDao: BaseDao<PokemonEntity> {
    @Transaction
    @Query("SELECT * FROM pokemon WHERE pokemonId BETWEEN :offset AND :limit ORDER BY pokemonId ASC")
    fun fetchAllPokemonWithAbilities(offset: Int, limit: Int): List<PokemonWithAbilities>
}