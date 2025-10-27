package za.co.dvt.pokeverse.features.menu.data.repository

import android.util.Log
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.features.menu.data.local.dataSource.MenuManagementLocalDataSource
import za.co.dvt.pokeverse.features.menu.domain.repository.MenuManagementRepository

class MenuManagementRepositoryImpl(
    private val menuManagementLocalDataSource: MenuManagementLocalDataSource
): MenuManagementRepository {
    override suspend fun saveDarkMode(isDarkMode: Boolean): Result<Boolean> {
        try {
            menuManagementLocalDataSource.saveDarkMode(isDarkMode)
            return Result.Success(true)
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Result.Error(e.message ?: "")
        }
    }

    override suspend fun fetchDarkMode(): Result<Boolean> {
        try {
            val isDarkMode = menuManagementLocalDataSource.fetchDarkMode()
            return if (isDarkMode != null) {
                Result.Success(isDarkMode)
            } else {
                Log.w(this.javaClass.name, "No dark mode saved.")
                return Result.Error("No dark mode saved.")
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Result.Error(e.message ?: "")
        }
    }
}