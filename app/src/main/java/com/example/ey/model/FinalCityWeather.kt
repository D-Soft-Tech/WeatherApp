package com.example.ey.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.* // ktlint-disable no-wildcard-imports

@Parcelize
data class FinalCityWeather(
    val cityIcon: Int,
    val dataFromMapperClass: WeatherDomainModel
) : Parcelable
