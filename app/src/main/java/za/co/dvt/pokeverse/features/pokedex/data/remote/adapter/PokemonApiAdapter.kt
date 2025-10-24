package za.co.dvt.pokeverse.features.pokedex.data.remote.adapter

import za.co.dvt.pokeverse.features.pokedex.data.remote.api.PokemonApi
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.common.data.remote.common.ApiResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.implementation.PokeApiImpl
import za.co.dvt.pokeverse.common.data.remote.infrastructure.ktor.KtorClientHelper

class PokemonApiAdapter(
    private val adaptee: PokeApiImpl
): PokemonApi {
    override suspend fun fetchPokemon(): ApiResponse<PokemonListResponse> {
        return KtorClientHelper.serviceCall {
            adaptee.fetchPokemon()
        }
    }
}