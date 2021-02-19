package grant.com.weatherapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import grant.com.weatherapp.model.WeatherCurrent

@Dao
interface WeatherCurrentDAO {

    @Query("SELECT * FROM current_weather WHERE dao_id = :id LIMIT 1")
    suspend fun findCurrentWeatherById(id: Long): WeatherCurrent?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currentWeather:WeatherCurrent): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg currentWeather:WeatherCurrent)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(currentWeather:WeatherCurrent)

    @Query("DELETE FROM current_weather")
    suspend fun deleteAll()

    @get:Query("SELECT * FROM current_weather LIMIT 1")
    val allTimes: WeatherCurrent

}