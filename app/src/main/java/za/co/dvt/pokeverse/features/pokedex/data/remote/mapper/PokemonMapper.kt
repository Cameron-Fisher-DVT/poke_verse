package za.co.dvt.pokeverse.features.pokedex.data.remote.mapper

import za.co.dvt.pokeverse.features.pokedex.data.remote.api.model.pokemon.PokemonListResponse
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon

object PokemonMapper {
    fun mapToDomainList(pokemonListResponse: PokemonListResponse): List<Pokemon> {
        return pokemonListResponse.pokemonResultDtoList.map {
            Pokemon(
                name = it.name,
                url = it.url
            )
        }
    }
}