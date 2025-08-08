package com.ideasprojects.cesar_londono_20250807.presentation.main.state

import com.ideasprojects.cesar_londono_20250807.domain.model.BitcoinDataPoint

data class MainState(
    val isLoading: Boolean = false,
    val bitcoinValues: List<BitcoinDataPoint> = emptyList(),
    val error: String? = null
)
