package za.co.dvt.pokeverse.features.pokedex.domain.usecase

import za.co.dvt.pokeverse.features.pokedex.domain.model.pokemon.PokemonInformation
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository
import za.co.dvt.pokeverse.common.domain.common.Result

class FetchPokemonInformationUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke(pokemonId: String): Result<PokemonInformation> {
        return pokedexRepository.fetchPokemonInformation(pokemonId)
    }
}