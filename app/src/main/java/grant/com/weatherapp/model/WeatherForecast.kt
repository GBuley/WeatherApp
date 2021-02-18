package grant.com.weatherapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherForecast(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<Day>,
    val city: City
):Parcelable