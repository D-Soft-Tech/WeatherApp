package com.example.ey.ui.recyclerView

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.ey.R

@BindingAdapter("loadCityIcon")
fun loadImageWithCoil(imageView: ImageView, drawable: Int) {
    imageView.load(drawable){
        crossfade(true)
        crossfade(1000)
        placeholder(R.drawable.image_load_placeholder)
    }
}