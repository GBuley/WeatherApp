package grant.com.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grant.com.weatherapp.R
import grant.com.weatherapp.databinding.ItemForecastBinding
import grant.com.weatherapp.model.Day
import grant.com.weatherapp.model.WeatherForecast
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class WeatherAdapter(private var forecastWeather : WeatherForecast) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(private var binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root){
        fun load(weatherDay: Day, forecastWeather: WeatherForecast){
            binding.dayTemp.text = weatherDay.main.temp.toInt().toString()
            val cityTimeZone = forecastWeather.city.timezone
            val day = Instant.ofEpochSecond(weatherDay.dt.toLong()+cityTimeZone).atOffset(ZoneOffset.UTC).dayOfWeek
            val time = Instant.ofEpochSecond(weatherDay.dt.toLong()+cityTimeZone).atOffset(ZoneOffset.UTC).toLocalTime()
            binding.dayOfWeek.text = day.toString()+ " at "+time
            if(weatherDay.weather.get(0).description.contains("cloud")){
                binding.imageDayType.setImageResource(R.drawable.ic_cloudy_svgrepo_com)
            }else if(weatherDay.weather.get(0).description.contains("rain")){
                binding.imageDayType.setImageResource(R.drawable.ic_rainy_svgrepo_com)
            }else if(weatherDay.weather.get(0).description.contains("snow")){
                binding.imageDayType.setImageResource(R.drawable.ic_snowy_snow_svgrepo_com)
            }else{
                binding.imageDayType.setImageResource(R.drawable.ic_sunny)
            }
        }

        fun check(position: Int): Boolean {
            val remainder = position%8
            if(remainder == 0){
                return true
            }
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding : ItemForecastBinding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.load(forecastWeather.list.get(position), forecastWeather)
        
    }

    override fun getItemCount(): Int {
        return forecastWeather.list.count()
    }
}