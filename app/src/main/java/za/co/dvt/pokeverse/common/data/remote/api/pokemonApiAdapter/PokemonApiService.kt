package za.co.dvt.pokeverse.common.data.remote.api.pokemonApiAdapter

import za.co.dvt.pokeverse.common.data.remote.api.model.pokemon.PokemonResponse

interface PokemonApiService {
    suspend fun fetchPokemon(): PokemonResponse
}