package com.example.ey.ui.recyclerView

import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.SuperscriptSpan
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.ey.R

@BindingAdapter("loadCityIcon")
fun loadImageWithCoil(imageView: ImageView, drawable: Int) {
    imageView.load(drawable) {
        crossfade(true)
        crossfade(1000)
        placeholder(R.drawable.image_load_placeholder)
    }
}

@BindingAdapter("tempToString")
fun tempToString(textView: TextView, temperature: Double?): SpannableStringBuilder {
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

@BindingAdapter("getStringWithDegree")
fun getValueInDegree(tv: TextView, value: Int?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tv.text = Html.fromHtml("$value<sup>o</sup>", Html.FROM_HTML_MODE_COMPACT)
    } else {
        tv.text = Html.fromHtml("$value<sup>o</sup>")
    }
}

@BindingAdapter("getTempInCels")
fun getTempInCelsius(tv: TextView, value: Double?) {
    val tempInCelsius = "${(value?.toInt() ?: 0) - 273.0}"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tv.text = Html.fromHtml("$tempInCelsius <sup>o</sup>C", Html.FROM_HTML_MODE_COMPACT)
    } else {
        tv.text = Html.fromHtml("$tempInCelsius <sup>o</sup>C")
    }
}

fun ProgressBar.show() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    this.visibility = View.GONE
}
