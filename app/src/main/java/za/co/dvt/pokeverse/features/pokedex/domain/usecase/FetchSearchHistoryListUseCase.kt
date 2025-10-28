package za.co.dvt.pokeverse.features.pokedex.domain.usecase

import za.co.dvt.pokeverse.features.pokedex.domain.model.search.SearchHistory
import za.co.dvt.pokeverse.features.pokedex.domain.repository.PokedexRepository
import za.co.dvt.pokeverse.common.domain.common.Result

class FetchSearchHistoryListUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke(): Result<List<SearchHistory>> {
        return pokedexRepository.fetchSearchHistoryList()
    }
}