package com.ideasprojects.cesar_londono_20250807.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasprojects.cesar_londono_20250807.presentation.login.state.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            // Simula un retraso de red
            delay(1500)

            if (username == "admin" && password == "1234") {
                _state.update { it.copy(isLoading = false, loginSuccess = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = false, loginSuccess = false, error = "Credenciales incorrectas") }
            }
        }
    }

    fun onLoginErrorShown() {
        _state.update { it.copy(error = null) }
    }
}
