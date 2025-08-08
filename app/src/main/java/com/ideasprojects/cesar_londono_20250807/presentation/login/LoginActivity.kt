package com.ideasprojects.cesar_londono_20250807.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ideasprojects.cesar_londono_20250807.presentation.main.MainActivity

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()

            LaunchedEffect(key1 = state.loginSuccess) {
                if (state.loginSuccess) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }
            }

            LaunchedEffect(key1 = state.error) {
                state.error?.let {
                    Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                    viewModel.onLoginErrorShown()
                }
            }

            LoginScreen(
                state = state,
                onLogin = viewModel::login,
                onBackClicked = { finish() }
            )
        }
    }
}
