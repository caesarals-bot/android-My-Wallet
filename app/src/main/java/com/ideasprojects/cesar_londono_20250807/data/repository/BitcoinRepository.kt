package com.ideasprojects.cesar_londono_20250807.data.repository

import com.ideasprojects.cesar_londono_20250807.data.remote.MindicadorApiService
import com.ideasprojects.cesar_londono_20250807.domain.model.BitcoinDataPoint

class BitcoinRepository {

    private val apiService = MindicadorApiService.create()

    suspend fun getBitcoinValues(): Result<List<BitcoinDataPoint>> {
        return try {
            val response = apiService.getBitcoinValues()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse.series)
                } else {
                    Result.failure(Exception("La respuesta de la API está vacía"))
                }
            } else {
                Result.failure(Exception("Error en la respuesta de la API: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
