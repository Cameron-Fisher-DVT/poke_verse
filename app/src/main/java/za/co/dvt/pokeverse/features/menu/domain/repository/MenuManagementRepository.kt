package za.co.dvt.pokeverse.features.menu.domain.repository

import za.co.dvt.pokeverse.common.domain.common.Result

interface MenuManagementRepository {
    suspend fun saveDarkMode(isDarkMode: Boolean): Result<Boolean>
    suspend fun fetchDarkMode(): Result<Boolean>
}