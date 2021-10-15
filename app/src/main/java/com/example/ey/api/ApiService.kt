package com.example.ey.api

import com.example.ey.model.CityWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): CityWeather
}
