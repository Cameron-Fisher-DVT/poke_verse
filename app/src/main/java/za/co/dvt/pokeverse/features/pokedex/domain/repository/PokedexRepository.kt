package za.co.dvt.pokeverse.features.pokedex.domain.repository

import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonInformation

interface PokedexRepository {
    suspend fun fetchPokemonList(offset: Int, limit: Int): Result<List<Pokemon>>
    suspend fun savePokemonList(pokemonList: List<Pokemon>): Result<String>
    suspend fun fetchPokemonInformation(pokemonId: String): Result<PokemonInformation>
    suspend fun updatePokemon(pokemon: Pokemon): Result<Pokemon>
}