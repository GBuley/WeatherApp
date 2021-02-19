package grant.com.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.model.dao.WeatherDb
import grant.com.weatherapp.remote.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentWeather = MutableLiveData<WeatherCurrent>()
    val currentWeather :LiveData<WeatherCurrent> = _currentWeather

    private val _forecastWeather = MutableLiveData<WeatherForecast>()
    val forecastWeather: LiveData<WeatherForecast> = _forecastWeather

    fun getCurrentWeather(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentWeather.postValue(WeatherRepo.getCurrentWeatherForCity(cityName))
            getCurrentWeather2(cityName)
        }
    }
    fun getWeatherForecast(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) { _forecastWeather.postValue(WeatherRepo.getForecastWeatherForCity(cityName)) }
    }


    fun getWeatherForecast2(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentWeather.postValue(WeatherRepo.getCurrentWeatherForCity(cityName))
            //fetch data then save data and then get data from the db
//            val weatherCurrentDAO : WeatherDb.getDatabase(context)?.
        }
    }
    fun getCurrentWeather2(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) { WeatherRepo.getCurrentWeatherForCity(getApplication(), cityName) }
    }
}
