package grant.com.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import grant.com.weatherapp.R
import grant.com.weatherapp.WeatherFragmentArgs
import grant.com.weatherapp.databinding.ActivityMainBinding
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>(){
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
    private var mainCurrentWeather : WeatherCurrent? = null
    private var mainForecastWeather : WeatherForecast? = null
    private var bundle: Bundle = Bundle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.weather_fragment) as NavHostFragment
        val navController = navHostFragment.navController





    }

}