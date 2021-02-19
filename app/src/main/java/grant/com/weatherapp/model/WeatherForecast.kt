package grant.com.weatherapp.model


import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@Entity(
    tableName = "weather_forecast"
)
@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherForecast(
    val cod: String,
    val message: Int,
    val cnt: Int


):Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wf_id") var wfId: Long = 0
    @Ignore var list: List<Day> = listOf()
    @Ignore var city: City = City(0, "", CoordX(0.0,0.0), "", 0, 0, 0, 0)
}