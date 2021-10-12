package com.example.ey.constant

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.SuperscriptSpan
import com.example.ey.R

object AppConstants {

    const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    val CITY_ICONS = mapOf<String, Int>(
        "kenya" to R.drawable.ic_kenya,
        "cairo" to R.drawable.ic_cairo,
        "lagos" to R.drawable.ic_lagos,
        "abuja" to R.drawable.ic_abuja,
        "new york" to R.drawable.ic_newyork,
        "texas" to R.drawable.ic_texas,
        "amazon" to R.drawable.ic_amazon,
        "belarus" to R.drawable.ic_belarus,
        "lesotho" to R.drawable.ic_lesotho,
        "jakarta" to R.drawable.ic_jakarta,
        "ankara" to R.drawable.ic_ankara2,
        "kano" to R.drawable.ic_kano2,
        "peru" to R.drawable.ic_pery,
        "winipeg" to R.drawable.ic_winipeg,
        "bagdad" to R.drawable.ic_bagdad,
        "westham" to R.drawable.ic_westham
    )

    fun tempToString(temperature: Double?): SpannableStringBuilder {
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