package za.co.dvt.pokeverse.features.menu.domain.usecase

import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.menu.domain.repository.MenuManagementRepository

class FetchDarkModeUseCase(
    private val menuManagementRepository: MenuManagementRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return menuManagementRepository.fetchDarkMode()
    }
}