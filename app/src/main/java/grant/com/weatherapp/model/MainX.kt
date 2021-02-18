package grant.com.weatherapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MainX(
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "temp_min")
    val tempMin: Double,
    @Json(name = "temp_max")
    val tempMax: Double,
    val pressure: Int,
    @Json(name = "sea_level")
    val seaLevel: Int,
    @Json(name = "grnd_level")
    val grndLevel: Int,
    val humidity: Int,
    @Json(name = "temp_kf")
    val tempKf: Double
) : Parcelable