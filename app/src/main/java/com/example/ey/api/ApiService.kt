package com.example.ey.api

import com.example.ey.model.CityWeather
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("weather?q={city}&appid=6e76055f1d172fe00a7aa9edd1f0ebf5")
    suspend fun getWeather(@Path("city") city: String): CityWeather
}
