package za.co.dvt.pokeverse.common.data.remote.api

import za.co.dvt.pokeverse.common.data.remote.api.model.pokemon.PokemonListResponse

interface PokemonApi {
    suspend fun fetchPokemon(): PokemonListResponse
}