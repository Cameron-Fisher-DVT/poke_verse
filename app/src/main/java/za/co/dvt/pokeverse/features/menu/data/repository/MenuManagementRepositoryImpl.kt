package za.co.dvt.pokeverse.features.menu.data.repository

import android.util.Log
import za.co.dvt.pokeverse.R
import za.co.dvt.pokeverse.common.domain.common.Result
import za.co.dvt.pokeverse.common.manager.resourceManager.ResourceManager
import za.co.dvt.pokeverse.features.menu.data.local.dataSource.MenuManagementLocalDataSource
import za.co.dvt.pokeverse.features.menu.domain.repository.MenuManagementRepository

class MenuManagementRepositoryImpl(
    private val menuManagementLocalDataSource: MenuManagementLocalDataSource,
    private val resourceManager: ResourceManager
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
                Log.w(this.javaClass.name, resourceManager.getString(R.string.menu_no_dark_mode_saved))
                return Result.Error(resourceManager.getString(R.string.menu_no_dark_mode_saved))
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: "")
            return Result.Error(e.message ?: "")
        }
    }
}