package grant.com.weatherapp.local

import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import grant.com.weatherapp.model.Main
import com.squareup.moshi.adapter

class Converters {
    @ExperimentalStdlibApi
    @TypeConverters
    fun mainWeatherToString(main : Main):String{
        val adapter = Moshi.Builder().build().adapter<Main>()
        return adapter.toJson(main)
    }

    @ExperimentalStdlibApi
    @TypeConverters
    fun mainStringToMain(mainString : String): Main? {
        val adapter = Moshi.Builder().build().adapter<Main>()
        return adapter.fromJson(mainString)
    }
}