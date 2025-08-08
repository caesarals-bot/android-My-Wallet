package com.ideasprojects.cesar_londono_20250807.data.remote

import com.ideasprojects.cesar_londono_20250807.domain.model.BitcoinApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MindicadorApiService {

    @GET("api/bitcoin")
    suspend fun getBitcoinValues(): Response<BitcoinApiResponse>

    companion object {
        private const val BASE_URL = "https://mindicador.cl/"

        fun create(): MindicadorApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MindicadorApiService::class.java)
        }
    }
}
