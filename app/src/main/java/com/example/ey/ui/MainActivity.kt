package com.example.ey.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ey.R
import com.example.ey.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_EY)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        toolbar = _binding.myToolbar

        val controller = findNavController(R.id.nav_fragment)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(controller)
        setupActionBarWithNavController(controller, null)
    }
}
