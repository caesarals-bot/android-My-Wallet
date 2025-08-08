package com.ideasprojects.cesar_londono_20250807.presentation.home.state

data class HomeState(
    val isLoading: Boolean = false,
    val latestBitcoinValue: Double? = null,
    val error: String? = null
)
