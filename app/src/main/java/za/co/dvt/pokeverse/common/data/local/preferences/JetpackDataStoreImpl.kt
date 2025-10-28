package za.co.dvt.pokeverse.common.data.local.preferences

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class JetpackDataStoreImpl(
    private val application: Application
): PreferencesManager {
    private val Application.dataStore by preferencesDataStore("pokemon_preferences")

    override suspend fun saveBoolean(key: String, value: Boolean) {
        application.dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun fetchBoolean(key: String): Boolean? {
        return application.dataStore.data.map {
            it[booleanPreferencesKey(key)]
        }.first()
    }
}

