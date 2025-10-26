package za.co.dvt.pokeverse.features.pokedex.domain.usecase

import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.Pokemon
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository
import za.co.dvt.pokeverse.common.domain.common.Result

class UpdatePokemonUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke(pokemon: Pokemon): Result<Pokemon> {
        return pokedexRepository.updatePokemon(pokemon)
    }
}