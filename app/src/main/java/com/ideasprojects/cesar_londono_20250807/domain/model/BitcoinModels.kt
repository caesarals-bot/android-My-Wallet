package com.ideasprojects.cesar_londono_20250807.domain.model

import com.google.gson.annotations.SerializedName

// Representa la respuesta completa de la API
data class BitcoinApiResponse(
    @SerializedName("codigo") val code: String,
    @SerializedName("nombre") val name: String,
    @SerializedName("unidad_medida") val unit: String,
    @SerializedName("serie") val series: List<BitcoinDataPoint>
)

// Representa un punto de datos individual en la serie temporal
data class BitcoinDataPoint(
    @SerializedName("fecha") val date: String,
    @SerializedName("valor") val value: Double
)
