package com.ideasprojects.cesar_londono_20250807.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ideasprojects.cesar_londono_20250807.data.UserPreferencesRepository
import com.ideasprojects.cesar_londono_20250807.data.repository.BitcoinRepository
import com.ideasprojects.cesar_londono_20250807.presentation.main.state.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferencesRepository = UserPreferencesRepository(application)
    private val bitcoinRepository = BitcoinRepository()

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    val username = userPreferencesRepository.username
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    init {
        loadBitcoinData()
    }

    private fun loadBitcoinData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = bitcoinRepository.getBitcoinValues()
            result.onSuccess { bitcoinList ->
                _state.update { it.copy(isLoading = false, bitcoinValues = bitcoinList) }
            }.onFailure { exception ->
                _state.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            userPreferencesRepository.clearSession()
        }
    }
}
