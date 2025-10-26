package za.co.dvt.pokeverse.features.pokedex.data.local.dataSource

import za.co.dvt.pokeverse.common.data.local.common.DatabaseResponse
import za.co.dvt.pokeverse.common.data.local.model.PokemonEntity
import za.co.dvt.pokeverse.common.data.local.model.PokemonWithAbilities

interface PokemonLocalDataSource {
    suspend fun fetchAllPokemonWithAbilities(): DatabaseResponse<List<PokemonWithAbilities>>
    suspend fun savePokemonList(pokemonEntityList: List<PokemonEntity>): DatabaseResponse<String>
}