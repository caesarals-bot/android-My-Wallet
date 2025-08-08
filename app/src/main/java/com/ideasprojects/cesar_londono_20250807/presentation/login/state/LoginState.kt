package com.ideasprojects.cesar_londono_20250807.presentation.login.state

data class LoginState(
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null
)
