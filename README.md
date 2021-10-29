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
6. Architecture (MVVM)
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
In this project the result from the api in the networkcall was returned as a flow, this enables us to turn the api into an observable and get live update (if any) from it at any time. you can view the [api service here](app/src/main/java/com/example/ey/api/ApiService.kt)

- **LiveData** 
Although, Flow is used in this project, it is converted to a LiveData in the viewmodel and the UI controller is made to observe the LiveData. This is because Live
Data is lifecycle aware and can detect when the observer is not active while flow will continue to dispatch update even when the observer is not active and this can cause a crash. Also in this project no localDatabase (such as Room database) was used. This is because their is no logical reason to persist a weather information locally for these values are constantly changing hence it only made sense to get the current and the correct information from the remote when needed.

- **Architecture**
The MVVM architecture is used in this project. Hence we have separation of concerns into the following classes: 
1. [ApiService](app/src/main/java/com/example/ey/api/ApiService.kt) This specifies functions that connects to the remote server.
2. [Repository](app/src/main/java/com/example/ey/api/Repository.kt) This is the class that serves has the single source of truth to the ViewModel.
3. [ViewModel](app/src/main/java/com/example/ey/ui/MainViewModel.kt) This is used to handle the business logic and used to help the UI controller to survive configuration changes with the help of the LiveData.

- **DaggerHilt** 
In this project, dependency injection was achieved with the help of DaggerHilt library. The Retrofit client was provided in a module using DaggerHilt as shown below:
```
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
```

- **DataBinding:** 
The databinding library was used in this project as it helps to further separate concerns by handling the business of binding the view to the data source right in the [xml layout](app/src/main/res/layout/listings_rv_item_view.xml). Also the binding adapters used in this project can be seen below: 
```
@BindingAdapter("loadCityIcon")
fun loadImageWithCoil(imageView: ImageView, drawable: Int) {
    imageView.load(drawable) {
        crossfade(true)
        crossfade(1000)
        placeholder(R.drawable.image_load_placeholder)
    }
}

@BindingAdapter("getStringWithDegree")
fun getValueInDegree(tv: TextView, value: Int?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tv.text = Html.fromHtml("$value<sup>o</sup>", Html.FROM_HTML_MODE_COMPACT)
    } else {
        tv.text = Html.fromHtml("$value<sup>o</sup>")
    }
}

@BindingAdapter("getTempInCels")
fun getTempInCelsius(tv: TextView, value: Double?) {
    val tempInCelsius = "${(value?.toInt() ?: 0) - 273.0}"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tv.text = Html.fromHtml("$tempInCelsius <sup>o</sup>C", Html.FROM_HTML_MODE_COMPACT)
    } else {
        tv.text = Html.fromHtml("$tempInCelsius <sup>o</sup>C")
    }
}
```

- **COIL** (Coroutines Image Loader): 
This, instead of Glide, was used for image loading in this project because it by default performs this operation on the background thread using coroutines

- **Navigation Component** This project uses the a single activity architecture. I also used it to manage the navigation via the navController.

- **DiffUtill** This class was used to handle setting of data to the recyclerview adapter. I used it to remove the stress of calling notifyDataSetChanged(), notifyItemInserted() and the rest. You can view this class [here](app/src/main/java/com/example/ey/ui/recyclerView/recyclerViewDiffUtil.kt).

# Features
Other features add to this project was the ability to search and filter the adapter at the OnTextChanged of the search view. [Veiw code here](app/src/main/java/com/example/ey/ui/fragments/ListingsScreen.kt)


