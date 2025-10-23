package za.co.dvt.pokeverse.common.data.remote.api.pokemonApiAdapter

import za.co.dvt.pokeverse.common.data.remote.api.model.pokemon.PokemonResponse
import za.co.dvt.pokeverse.common.data.remote.ktorClient.KtorClientHelper

class PokemonApiAdapter(
    private val adaptee: PokemonApiServiceImpl
): PokemonApiService {
    override suspend fun fetchPokemon(): PokemonResponse {
        return KtorClientHelper.serviceCall {
            adaptee.fetchPokemon()
        }
    }
}