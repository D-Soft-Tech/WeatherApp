package com.example.ey.ui.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.ey.model.FinalCityWeather

class RecyclerViewDiffUtil(
    private val oldList: List<FinalCityWeather>,
    private val newList: List<FinalCityWeather>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].dataFromMapperClass.cityName ==
            newList[newItemPosition].dataFromMapperClass.cityName

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].dataFromMapperClass.cityName ==
            newList[newItemPosition].dataFromMapperClass.cityName
}
