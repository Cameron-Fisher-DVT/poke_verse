package za.co.dvt.pokeverse.common.data.remote.api.pokemonApiAdapter

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import za.co.dvt.pokeverse.common.data.remote.constants.ApiConstants
import za.co.dvt.pokeverse.common.data.remote.ktorClient.KtorClient

class PokemonApiServiceImpl: KtorClient() {
    suspend fun fetchPokemon(): HttpResponse {
        return client.get(ApiConstants.POKEMON_ENDPOINT)
    }
}