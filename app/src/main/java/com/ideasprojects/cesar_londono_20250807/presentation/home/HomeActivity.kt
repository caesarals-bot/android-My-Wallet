package com.ideasprojects.cesar_londono_20250807.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ideasprojects.cesar_londono_20250807.presentation.login.LoginActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(onLoginClicked = {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            })
        }
    }
}
