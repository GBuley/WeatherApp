package grant.com.weatherapp.remote

import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.utils.Constants

object WeatherRepo {

    val API_KEY = "7425cb3b652b028422abf913d1cf458e"

    suspend fun getCurrentWeatherForCity(cityName : String) : WeatherCurrent{
        return RetroFitInstance.jobService.getCurrentWeatherForCity(cityName, API_KEY, "imperial")
    }
    suspend fun getForecastWeatherForCity(cityName : String) : WeatherForecast {
        return RetroFitInstance.jobService.getForecastWeatherForCity(cityName, API_KEY, "imperial")
    }
}