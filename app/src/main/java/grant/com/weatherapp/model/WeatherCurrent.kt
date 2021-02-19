package grant.com.weatherapp.model


import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "current_weather",
    indices = [Index(value = ["dt_epoch"], unique = true)]
)

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherCurrent @JvmOverloads constructor(
        val base: String,
        val visibility: Int,
        @ColumnInfo(name = "dt_epoch") val dt: Int,
        val timezone: Int,
        val id: Int,
        val name: String,
        val cod: Int
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="dao_id") var doaid: Long = 0
    @Ignore var sys: Sys = Sys(0,0,"",0,0)
    @Ignore var main: Main = Main(0.0, 0.0, 0.0,0.0, 0, 0)
    @Ignore var coord: Coord = Coord(0.0, 0.0)
    @Ignore var weather: List<Weather> = listOf()
    @Ignore var wind: Wind? = null
    @Ignore var snow: Snow? = null
    @Ignore var clouds: Clouds? = null
}
