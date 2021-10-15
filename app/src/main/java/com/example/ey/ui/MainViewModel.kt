package com.example.ey.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ey.api.Repository
import com.example.ey.constant.AppConstants.CITY_ICONS
import com.example.ey.model.FinalCityWeather
import com.example.ey.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _data: MutableLiveData<List<FinalCityWeather>> =
        MutableLiveData<List<FinalCityWeather>>()

    private val _hasAllCitiesBeenLoaded: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val hasAllCitiesBeenLoaded get() = _hasAllCitiesBeenLoaded

    fun loadWeather() {
        val listOfIcons = CITY_ICONS
        val holder = mutableListOf<FinalCityWeather>()

        listOfIcons.forEach { (string, value) ->
            viewModelScope.launch {
                repository.getWeather(string).collect { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            holder.add(
                                FinalCityWeather(
                                    value,
                                    response.data!!
                                )
                            )
                        }
                    }
                    _hasAllCitiesBeenLoaded.postValue(
                        holder.size == 16
                    )
                    if (string == "westham") {
                        _data.value = holder
                    }
                }
            }
        }
    }

    fun getWeather() = _data
}
