package za.co.dvt.pokeverse.features.menu.data.local.dataSource

interface MenuManagementLocalDataSource {
    suspend fun saveDarkMode(isDarkMode: Boolean)
    suspend fun fetchDarkMode(): Boolean?
}