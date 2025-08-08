package com.ideasprojects.cesar_londono_20250807.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasprojects.cesar_londono_20250807.data.repository.BitcoinRepository
import com.ideasprojects.cesar_londono_20250807.presentation.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val bitcoinRepository = BitcoinRepository()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadLatestBitcoinValue()
    }

    private fun loadLatestBitcoinValue() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = bitcoinRepository.getBitcoinValues()
            result.onSuccess { bitcoinList ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        latestBitcoinValue = bitcoinList.firstOrNull()?.value
                    )
                }
            }.onFailure { exception ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
            }
        }
    }
}
