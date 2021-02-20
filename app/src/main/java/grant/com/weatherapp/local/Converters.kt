package grant.com.weatherapp.local

import androidx.room.Ignore
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import grant.com.weatherapp.model.*


@ExperimentalStdlibApi
class Converters {

    @TypeConverter
    fun mainWeatherToString(main : Main):String{
        val adapter = Moshi.Builder().build().adapter<Main>()
        return adapter.toJson(main)
    }

    @TypeConverter
    fun mainStringToMain(mainString : String): Main? {
        val adapter = Moshi.Builder().build().adapter<Main>()
        return adapter.fromJson(mainString)?: Main(0.0, 0.0, 0.0,0.0, 0, 0)
    }

    @TypeConverter
    fun currentWeatherToString(weather : List<Weather>):String{
        val adapter = Moshi.Builder().build().adapter<List<Weather>>()
        return adapter.toJson(weather)
    }

    @TypeConverter
    fun weatherStringToWeather(weatherString : String): List<Weather>? {
        val adapter = Moshi.Builder().build().adapter<List<Weather>>()
        return adapter.fromJson(weatherString)?: listOf()
    }

//
//    var sys: Sys = Sys(0,0,"",0,0)
//    var coord: Coord = Coord(0.0, 0.0)
//    var wind: Wind? = null
//    var snow: Snow? = null
//    var clouds: Clouds? = null
}