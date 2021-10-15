package com.example.ey.model

import android.os.Parcelable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.SuperscriptSpan
import kotlinx.parcelize.Parcelize
import java.util.* // ktlint-disable no-wildcard-imports

@Parcelize
data class FinalCityWeather(
    val cityIcon: Int,
    val dataFromMapperClass: WeatherDomainModel
) : Parcelable {

    fun getTemp(temperature: Double?): SpannableStringBuilder {
        val newTemp = "${(temperature?.toInt() ?: 0) - 273.0} oC"
        return SpannableStringBuilder(newTemp).apply {
            setSpan(
                SuperscriptSpan(),
                newTemp.indexOf("o"),
                newTemp.indexOf("o") + "o".length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}
