package za.co.dvt.pokeverse.features.menu.data.local.dataSource

import za.co.dvt.pokeverse.common.data.local.preferences.PreferencesManager

class MenuManagementLocalDataSourceImpl(
    private val preferencesManager: PreferencesManager
): MenuManagementLocalDataSource {
    private companion object {
        const val DARK_MODE = "dark_mode"
    }

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        preferencesManager.saveBoolean(DARK_MODE, isDarkMode)
    }

    override suspend fun fetchDarkMode(): Boolean? {
        return preferencesManager.fetchBoolean(DARK_MODE)
    }
}