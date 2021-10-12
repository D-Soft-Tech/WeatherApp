package com.example.ey.api

import com.example.ey.mapper.DomainClass
import com.example.ey.model.WeatherDomainModel
import com.example.ey.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class Repository @Inject constructor(
    val apiService: ApiService,
    val domain: DomainClass
) {

    suspend fun getWeather(cityName: String): Flow<Resource<WeatherDomainModel>> = flow {
        emit(Resource.error("No Result Found", null))

        try {

            val weather = apiService.getWeather(cityName)

            emit(Resource.success(domain.fromReturnedToList(weather)))
        } catch (e: Exception) {
            emit(Resource.error("No Result found", null))
        }
    }
}
