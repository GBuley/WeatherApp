package grant.com.weatherapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Day(
    val dt: Int,
    val main: MainX,
    val weather: List<WeatherX>,
    val clouds: CloudsX,
    val wind: WindX,
    val visibility: Int,
    val pop: Double,
    val snow: SnowX?,
    val sys: SysX,
    @Json(name = "dt_txt")
    val dtTxt: String,
    val rain: Rain?
) : Parcelable