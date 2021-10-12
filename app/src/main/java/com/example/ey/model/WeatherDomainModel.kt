package com.example.ey.model

import java.text.SimpleDateFormat
import java.util.Date

data class WeatherDomainModel(
    val cityName: String?,
    val countryAbr: String?,
    val summary: String?,
    val dateCollected: Int?,
    val temp: Double?,
    val mainTemp: Double?,
    val maxTemp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val windSpeed: Double?,
    val windSpeedDirection: Int?,
    val visibility: Int?
) {
    fun getDate() =
        dateCollected?.let {
            SimpleDateFormat("yyyy.MM.dd").format(Date(it.toLong()))
        }
}
