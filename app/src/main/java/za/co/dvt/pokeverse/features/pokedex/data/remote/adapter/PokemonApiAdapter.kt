package za.co.dvt.pokeverse.features.pokedex.data.remote.adapter

import za.co.dvt.pokeverse.common.data.remote.infrastructure.NetworkResponse
import za.co.dvt.pokeverse.common.data.remote.infrastructure.ktor.KtorClientHelper
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.PokemonApi
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemonInformationResponse.PokemonInformationResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.data.remote.implementation.PokeApiImpl

class PokemonApiAdapter(
    private val adaptee: PokeApiImpl
): PokemonApi {
    override suspend fun fetchPokemonListResponse(offset: Int, limit: Int): NetworkResponse<PokemonListResponse> {
        return KtorClientHelper.serviceCall {
            adaptee.fetchPokemonList(offset, limit)
        }
    }

    override suspend fun fetchPokemonInformationResponse(pokemonId: String): NetworkResponse<PokemonInformationResponse> {
        return KtorClientHelper.serviceCall {
            adaptee.fetchPokemonInformation(pokemonId)
        }
    }
}