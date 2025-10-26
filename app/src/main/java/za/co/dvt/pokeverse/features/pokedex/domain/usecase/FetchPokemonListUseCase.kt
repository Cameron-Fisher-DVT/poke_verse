package za.co.dvt.pokeverse.features.pokedex.domain.usecase

import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository

class FetchPokemonListUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>> = pokedexRepository.fetchPokemonList()
}