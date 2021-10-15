package com.example.ey.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ey.databinding.ListingsRvItemViewBinding
import com.example.ey.model.FinalCityWeather
import com.example.ey.ui.fragments.ListingsScreenDirections

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewViewHolder>() {
    private var listOfCityWeather: List<FinalCityWeather> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder =
        RecyclerViewViewHolder(
            ListingsRvItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(listOfCityWeather[position])
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    ListingsScreenDirections.actionListingsScreenToDetailsScreen(
                        listOfCityWeather[position]
                    )
                )
        }
    }

    override fun getItemCount() = listOfCityWeather.size

    fun setCitiesWeathers(newListOfCityWeather: List<FinalCityWeather>) {
        val diff = RecyclerViewDiffUtil(listOfCityWeather, newListOfCityWeather)
        val diffResult = DiffUtil.calculateDiff(diff)
        listOfCityWeather = newListOfCityWeather
        diffResult.dispatchUpdatesTo(this)
    }
}
