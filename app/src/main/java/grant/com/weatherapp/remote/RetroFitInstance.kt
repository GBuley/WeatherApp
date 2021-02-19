package grant.com.weatherapp.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetroFitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/"
    private val client = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }.let { OkHttpClient.Builder().addInterceptor(it).build() }

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).client(client).build()

    val weatherService by lazy { retrofit.create(WeatherService::class.java)}

}