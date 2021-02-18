package grant.com.weatherapp.remote

import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherForCity(@Query("q") cityName : String, @Query("appid") apiKey : String, @Query("units") units : String) : WeatherCurrent

    @GET("data/2.5/forecast")
    suspend fun getForecastWeatherForCity(@Query("q") cityName : String, @Query("appid") apiKey : String, @Query("units") units : String) : WeatherForecast
}