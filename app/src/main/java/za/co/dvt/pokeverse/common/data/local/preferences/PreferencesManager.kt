package za.co.dvt.pokeverse.common.data.local.preferences

interface PreferencesManager {
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun fetchBoolean(key: String): Boolean?
}