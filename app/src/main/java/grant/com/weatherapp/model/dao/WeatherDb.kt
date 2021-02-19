package grant.com.weatherapp.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import grant.com.weatherapp.local.Converters
import grant.com.weatherapp.model.WeatherCurrent
import grant.com.weatherapp.model.WeatherForecast

@ExperimentalStdlibApi
@TypeConverters(Converters::class)
@Database(entities = [WeatherCurrent::class], version = 3)
abstract class WeatherDb : RoomDatabase(){

    abstract fun weatherCurrentDAO():WeatherCurrentDAO

    companion object {
        private const val DB_NAME = "WeatherDb.db"
        private var INSTANCE: WeatherDb? = null

        fun getDatabase(context: Context): WeatherDb? {
            if (INSTANCE == null)
                synchronized(WeatherDb::class.java) {
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, WeatherDb::class.java, DB_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            return INSTANCE
        }
    }


}