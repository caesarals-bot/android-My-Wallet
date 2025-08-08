package com.ideasprojects.cesar_londono_20250807.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ideasprojects.cesar_londono_20250807.presentation.home.HomeActivity
import com.ideasprojects.cesar_londono_20250807.presentation.main.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isLoggedIn by viewModel.isLoggedIn.collectAsState()

            LaunchedEffect(isLoggedIn) {
                // Esperamos a que isLoggedIn tenga un valor (no sea null)
                if (isLoggedIn != null) {
                    val destination = if (isLoggedIn == true) {
                        MainActivity::class.java
                    } else {
                        HomeActivity::class.java
                    }
                    startActivity(Intent(this@SplashActivity, destination))
                    finish()
                }
            }

            SplashScreen()

            LaunchedEffect(key1 = true) {
                delay(3000) // Espera 3 segundos
            }
        }
    }
}
