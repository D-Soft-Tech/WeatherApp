package com.example.ey.ui.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.ey.databinding.ListingsRvItemViewBinding
import com.example.ey.model.FinalCityWeather

class RecyclerViewViewHolder(private val itemViewBinding: ListingsRvItemViewBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bind(data: FinalCityWeather) {
        itemViewBinding.cityWeather = data
        itemViewBinding.executePendingBindings()
    }
}
