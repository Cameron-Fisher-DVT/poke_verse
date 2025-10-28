package za.co.dvt.pokeverse.common.data.local.database

import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonAbilityDao
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonDao
import za.co.dvt.pokeverse.common.data.local.database.dao.SearchHistoryDao

interface ApplicationDatabase {
    fun pokemonDao(): PokemonDao
    fun pokemonAbilityDao(): PokemonAbilityDao
    fun searchHistoryDao(): SearchHistoryDao
}