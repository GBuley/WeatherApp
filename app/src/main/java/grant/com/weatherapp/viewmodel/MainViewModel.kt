package grant.com.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.remote.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _currentWeather = MutableLiveData<WeatherCurrent>()
    val currentWeather :LiveData<WeatherCurrent> = _currentWeather

    private val _forecastWeather = MutableLiveData<WeatherForecast>()
    val forecastWeather: LiveData<WeatherForecast> = _forecastWeather

    fun getCurrentWeather(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) { _currentWeather.postValue(WeatherRepo.getCurrentWeatherForCity(cityName)) }
    }
    fun getWeatherForecast(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) { _forecastWeather.postValue(WeatherRepo.getForecastWeatherForCity(cityName)) }
    }
}
