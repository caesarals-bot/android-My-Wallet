package com.ideasprojects.cesar_londono_20250807.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository(context: Context) {

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USERNAME = stringPreferencesKey("username")
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map {
        it[PreferencesKeys.IS_LOGGED_IN] ?: false
    }

    val username: Flow<String?> = dataStore.data.map {
        it[PreferencesKeys.USERNAME]
    }

    suspend fun saveSession(username: String) {
        dataStore.edit {
            it[PreferencesKeys.IS_LOGGED_IN] = true
            it[PreferencesKeys.USERNAME] = username
        }
    }

    suspend fun clearSession() {
        dataStore.edit {
            it.clear()
        }
    }
}
