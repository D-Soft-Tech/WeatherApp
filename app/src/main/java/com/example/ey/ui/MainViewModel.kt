package com.example.ey.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ey.api.Repository
import com.example.ey.constant.AppConstants.CITY_ICONS
import com.example.ey.model.FinalCityWeather
import com.example.ey.util.Resource
import com.example.ey.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _data: MutableLiveData<Resource<List<FinalCityWeather>>> =
        MutableLiveData<Resource<List<FinalCityWeather>>>()

    private val data: LiveData<Resource<List<FinalCityWeather>>>
        get() =
            _data

    private val _hasAllCitiesBeenLoaded: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val hasAllCitiesBeenLoaded get() = _hasAllCitiesBeenLoaded

    init {
        loadWeather()
    }

    private fun loadWeather() {
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
                        else -> {
                        } // Do nothing in the else part
                    }
                    _hasAllCitiesBeenLoaded.postValue(
                        holder.size == 16
                    )
                    if (holder.size == 16) {
                        _data.value = Resource.success(holder)
                    }
                }
            }
        }
    }

    fun getWeather() = data
}
