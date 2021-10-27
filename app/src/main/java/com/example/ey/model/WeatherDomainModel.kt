package com.example.ey.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date

@Parcelize
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
    val sunrise: Int?,
    val sunset: Int?,
    val visibility: Int?
) : Parcelable {
    fun getDate() =
        dateCollected?.let {
            SimpleDateFormat("MMM dd, yyyy.").format(Date(it.toLong()))
        }

    fun getTime(time: Int?) =
        time?.let {
            SimpleDateFormat("HH:mm:ss aa").format(Date(it.toLong()))
        }
}
