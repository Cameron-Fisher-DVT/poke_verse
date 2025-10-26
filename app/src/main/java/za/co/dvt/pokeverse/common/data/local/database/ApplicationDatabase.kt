package za.co.dvt.pokeverse.common.data.local.database

import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonAbilityDao
import za.co.dvt.pokeverse.common.data.local.database.dao.PokemonDao

interface ApplicationDatabase {
    fun pokemonDao(): PokemonDao
    fun pokemonAbilityDao(): PokemonAbilityDao
}