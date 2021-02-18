package grant.com.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grant.com.weatherapp.adapter.WeatherAdapter
import grant.com.weatherapp.databinding.FragmentWeatherBinding
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast
import grant.com.weatherapp.viewmodel.MainViewModel

class WeatherFragment : Fragment() {
    lateinit var binding: FragmentWeatherBinding
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getWeatherForecast("Garland")
        viewModel.getCurrentWeather("Garland")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentWeatherBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { weatherCurrent ->
            binding.degrees.text = weatherCurrent.main.temp.toInt().toString()+"℉"
            binding.tvCity.text = weatherCurrent.name + ", "+ weatherCurrent.sys.country
            val weatherDescription = weatherCurrent.weather.get(0).description
            binding.tvTypeOfWeather.text = weatherDescription
            val currentTime = weatherCurrent.dt
            val sunrise = weatherCurrent.sys.sunrise
            val sunset = weatherCurrent.sys.sunset
            if(currentTime>sunrise && currentTime<sunset) {
                if (weatherDescription.contains("cloud")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.cloudy_day)
                } else if (weatherDescription.contains("rain")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.rainy_day)
                } else if (weatherDescription.contains("clear")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.sunny_day)
                } else if (weatherDescription.contains("snow")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.snowy_day)
                } else {
                    binding.backgroundImage.setBackgroundResource(R.drawable.cloudy_day)
                }
            }
            else if(currentTime<sunrise || currentTime>sunset){
                if (weatherDescription.contains("cloud")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.cloudy_night)
                } else if (weatherDescription.contains("rain")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.rainy_night)
                } else if (weatherDescription.contains("clear")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.night)
                } else if (weatherDescription.contains("snow")) {
                    binding.backgroundImage.setBackgroundResource(R.drawable.snowy_night)
                } else {
                    binding.backgroundImage.setBackgroundResource(R.drawable.night)
                }
            }

        })

        viewModel.forecastWeather.observe(viewLifecycleOwner, Observer { weatherForecast ->
            val layoutManager = LinearLayoutManager(binding.root.context)
            val adapter = WeatherAdapter(weatherForecast)
            binding.rvWeatherList.layoutManager = layoutManager
            binding.rvWeatherList.adapter = adapter
        })


    }
}