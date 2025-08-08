package com.ideasprojects.cesar_londono_20250807.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ideasprojects.cesar_londono_20250807.domain.model.BitcoinDataPoint
import com.ideasprojects.cesar_londono_20250807.presentation.main.state.MainState
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MainScreen(
    username: String? = "Invitado",
    state: MainState = MainState(),
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido, ${username ?: "..."}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onLogout) {
            Text(text = "Cerrar Sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error?.let {
                Text(
                    text = "Error: $it",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.bitcoinValues) { dataPoint ->
                    BitcoinValueRow(dataPoint = dataPoint)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun BitcoinValueRow(dataPoint: BitcoinDataPoint) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = dataPoint.date.formatDate(), style = MaterialTheme.typography.bodyLarge)
        Text(text = dataPoint.value.formatCurrency(), fontWeight = FontWeight.Bold)
    }
}

private fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("es", "ES"))
        val date = inputFormat.parse(this)
        if (date != null) outputFormat.format(date) else this
    } catch (e: Exception) {
        this
    }
}

private fun Double.formatCurrency(): String {
    return try {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        format.maximumFractionDigits = 0
        format.format(this)
    } catch (e: Exception) {
        this.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        username = "Cesar",
        state = MainState(
            bitcoinValues = listOf(
                BitcoinDataPoint("2025-08-08T00:00:00.000Z", 65000000.0),
                BitcoinDataPoint("2025-08-07T00:00:00.000Z", 64500000.0)
            )
        )
    )
}
