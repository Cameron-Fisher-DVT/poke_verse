package za.co.dvt.pokeverse.features.pokedex.data.remote.implementation

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import za.co.dvt.pokeverse.common.data.remote.config.ApiConstants
import za.co.dvt.pokeverse.common.data.remote.infrastructure.ktor.KtorClient

class PokeApiImpl : KtorClient() {
    suspend fun fetchPokemonList(): HttpResponse {
        return client.get(ApiConstants.POKEMON_ENDPOINT)
    }

    suspend fun fetchPokemonInformation(pokemonId: String): HttpResponse {
        return client.get("${ApiConstants.POKEMON_INFORMATION_ENDPOINT}$pokemonId")
    }
}