package grant.com.weatherapp.remote

import android.content.Context
import android.util.Log
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.model.dao.WeatherDb
import java.util.*

@ExperimentalStdlibApi
object WeatherRepo {
    private const val TAG = "WeatherRepo"
    private const val TIMESTAMP_PREF = "TIMESTAMP_PREF"
    private const val TIMESTAMP_KEY = "TIMESTAMP_KEY"
    private const val API_KEY = "7425cb3b652b028422abf913d1cf458e"

    suspend fun getCurrentWeatherForCity(cityName : String) : WeatherCurrent{
        return RetroFitInstance.weatherService.getCurrentWeatherForCity(cityName, API_KEY, "imperial")
    }
    suspend fun getForecastWeatherForCity(cityName : String) : WeatherForecast {
        return RetroFitInstance.weatherService.getForecastWeatherForCity(cityName, API_KEY, "imperial")
    }
//    }

    //    suspend fun getCurrentWeatherForCity2(cityName : String) : WeatherCurrent{
//        return RetroFitInstance.jobService.getCurrentWeatherForCity(cityName, API_KEY, "imperial")
//    }
//    suspend fun getForecastWeatherForCity2(cityName : String) : WeatherForecast {
//        return RetroFitInstance.jobService.getForecastWeatherForCity(cityName, API_KEY, "imperial")

    suspend fun getCurrentWeatherForCity(context: Context, cityName: String) : WeatherCurrent? {

        val pref = context.getSharedPreferences(TIMESTAMP_PREF, Context.MODE_PRIVATE)
        val savedTime = pref.getLong(TIMESTAMP_KEY, 0)
        val currentTime = Calendar.getInstance()

        val savedTimeCalander = Calendar.getInstance().apply {
            timeInMillis = savedTime
            add(Calendar.MINUTE, 1)
        }
        val weatherCurrentDAO = WeatherDb.getDatabase(context)?.weatherCurrentDAO()

        if(currentTime.time > savedTimeCalander.time){
            pref.edit().putLong(TIMESTAMP_KEY, currentTime.timeInMillis).apply()
            val weatherCurrent = RetroFitInstance.weatherService.getCurrentWeatherForCity(cityName, API_KEY, "imperial")
            weatherCurrentDAO?.deleteAll()
            weatherCurrentDAO?.insert(weatherCurrent)
            Log.i(TAG, "Inserted knew WeatherCurrent")
        }
        return weatherCurrentDAO?.allTimes
    }
}
