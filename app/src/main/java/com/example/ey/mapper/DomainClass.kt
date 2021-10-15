package com.example.ey.mapper

import com.example.ey.model.CityWeather
import com.example.ey.model.WeatherDomainModel
import javax.inject.Inject

class DomainClass @Inject constructor() : NetworkResultMapper<CityWeather, WeatherDomainModel> {
    override fun fromReturnedToList(fromRemote: CityWeather): WeatherDomainModel =
        WeatherDomainModel(
            fromRemote.name,
            fromRemote.sys!!.country,
            fromRemote.weather?.get(0)?.description,
            fromRemote.dt,
            fromRemote.main!!.temp,
            fromRemote.main!!.temp_min,
            fromRemote.main!!.temp_max,
            fromRemote.main!!.pressure,
            fromRemote.main!!.humidity,
            fromRemote.wind!!.speed,
            fromRemote.wind!!.deg,
            fromRemote.sys!!.sunrise,
            fromRemote.sys!!.sunset,
            fromRemote.visibility
        )
}
