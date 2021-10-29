# WeatherApp (An Assessment submitted to EY)
WeatherApp is an application that gets the weather forecast of some top cities. This include:
* Kenya
* Cairo
* Texas
* Amazon
* Peru etc.

# Technologies Used
1. Kotlin
2. Kotlin Flow
3. Coroutines
4. Retrofit
5. LiveData
6. MVVM Architecture
7. Navigation Component
8. DaggerHilt
9. DataBinding
10. Coil
11. DiffUtill

# Implementation
The afforementioned technologies were implemented as follow:
- **Kotlin:** 
This project uses 100% Kotlin as the programming language. This is because Kotlin brings more features to the table. Features like extension functions, null safety and make things achievable with less verbose codes.
- **Coroutines:**
In this project Kotlin Coroutines was used to perform asynchronous operations like network call
```
@GET("weather")
suspend fun getWeather(
    @Query("q") q: String,
    @Query("appid") appid: String
): Flow<CityWeather>    
```

The code snippet above makes the networkcall and returned the result as a flow. This is done in a suspending function as this is not a main safe operation, this method was later called in a coroutines. see below: 
```
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
            else -> {} // Do nothing in the else part
        }
        _hasAllCitiesBeenLoaded.postValue(
            holder.size == 16
        )
        if (holder.size == 16) {
            _data.value = holder
        }
    }
}
```

- **Kotlin Flow:**
This is one of kotlin features that enables developers to perform long running operations asynchronously and transactionally.
In this project the result from the api in the networkcall was returned as a flow, this enables us to turn the api into an observable and get live update (if any) from it at any time. 




