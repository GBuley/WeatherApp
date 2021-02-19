package grant.com.weatherapp.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast

@Database(entities = [WeatherForecast::class], version = 1)
abstract class WeatherForecastDb : RoomDatabase(){

    abstract fun weatherForecastDAO():WeatherForecastDAO

    companion object {
        private const val DB_NAME = "WeatherForecastDb.db"
        private var INSTANCE: WeatherForecastDb? = null

        suspend fun getDatabase(context: Context): WeatherForecastDb? {
            if (INSTANCE == null)
                synchronized(WeatherForecastDb::class.java) {
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext, WeatherForecastDb::class.java, DB_NAME
                        ).build()
                    }
                }
            return INSTANCE
        }
    }


}