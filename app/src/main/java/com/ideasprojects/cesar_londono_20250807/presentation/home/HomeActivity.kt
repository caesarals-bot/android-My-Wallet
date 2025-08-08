package com.ideasprojects.cesar_londono_20250807.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ideasprojects.cesar_londono_20250807.presentation.login.LoginActivity

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()

            HomeScreen(
                state = state,
                onLoginClicked = {
                    startActivity(Intent(this, LoginActivity::class.java))
                },
                onRegisterClicked = {
                    // Lógica para el registro
                }
            )
        }
    }
}
