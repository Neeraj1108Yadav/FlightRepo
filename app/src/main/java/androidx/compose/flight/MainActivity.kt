package androidx.compose.flight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.flight.screens.HomeScreen
import androidx.compose.flight.screens.LandingScreen
import androidx.compose.flight.ui.theme.FlightScheduleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightScheduleTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    var showLandingScreen by remember { mutableStateOf(true) }
    if(showLandingScreen){
        LandingScreen({
            showLandingScreen = false
        })
    }else{
        HomeScreen()
    }
}