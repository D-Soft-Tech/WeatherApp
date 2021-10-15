package com.example.ey.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ey.R
import com.example.ey.databinding.DetailsScreenBinding

class DetailsScreen : Fragment() {

    val args by navArgs<DetailsScreenArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: DetailsScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.details_screen, container, false)
        binding.cityWeather = args.currentCityWeather
        return binding.root
    }
}
