package grant.com.weatherapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast

@Dao
interface WeatherForecastDAO {

    @Query("SELECT * FROM weather_forecast WHERE wf_id = :id LIMIT 1")
    suspend fun findForecastWeatherById(id: Long): WeatherForecast?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(forecastWeather: WeatherForecast): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg weatherForecast: WeatherForecast)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(forecastWeather: WeatherForecast)

    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAll()

    @get:Query("SELECT * FROM weather_forecast")
    val allTimes: LiveData<List<WeatherForecast>>
}