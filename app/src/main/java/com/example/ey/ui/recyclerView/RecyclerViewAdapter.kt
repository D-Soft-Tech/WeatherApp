package com.example.ey.ui.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ey.model.FinalCityWeather

class RecyclerViewAdapter(
    private val cityList: List<FinalCityWeather>
): RecyclerView.Adapter<RecyclerViewViewHolder>() {
    private var listOfCityWeather: List<FinalCityWeather> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

    fun setCities(newListOfCityWeather: List<FinalCityWeather>){
        val diff = RecyclerViewDiffUtil(listOfCityWeather, newListOfCityWeather)
        val diffResult = DiffUtil.calculateDiff(diff)
        listOfCityWeather = newListOfCityWeather
        diffResult.dispatchUpdatesTo(this)
    }
}