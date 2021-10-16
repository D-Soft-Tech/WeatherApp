package com.example.ey.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ey.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_EY)
        setContentView(R.layout.activity_main)
    }
}
