package za.co.dvt.pokeverse.common.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import za.co.dvt.pokeverse.common.data.local.database.DatabaseConstants.DATABASE_EXPORT_SCHEMA
import za.co.dvt.pokeverse.common.data.local.database.DatabaseConstants.DATABASE_NAME
import za.co.dvt.pokeverse.common.data.local.database.DatabaseConstants.DATABASE_VERSION
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonAbilityDao
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonDao
import za.co.dvt.pokeverse.common.data.local.database.dao.SearchHistoryDao
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonAbilityEntity
import za.co.dvt.pokeverse.common.data.local.model.pokemon.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.search.SearchHistoryEntity

@Database(
    entities = [PokemonEntity::class, PokemonAbilityEntity::class, SearchHistoryEntity::class],
    version = DATABASE_VERSION,
    exportSchema = DATABASE_EXPORT_SCHEMA
)
abstract class JetpackRoomDBImpl : RoomDatabase(), ApplicationDatabase {
    abstract override fun pokemonDao(): PokemonDao
    abstract override fun pokemonAbilityDao(): PokemonAbilityDao
    abstract override fun searchHistoryDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: JetpackRoomDBImpl? = null

        fun getDatabase(context: Context): JetpackRoomDBImpl {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        JetpackRoomDBImpl::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}