import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.weatherapp.presentation.theme.Shapes
import com.example.weatherapp.presentation.theme.Typography

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}