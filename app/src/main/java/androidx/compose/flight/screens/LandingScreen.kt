package androidx.compose.flight.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

private const val SplashWaitTime:Long = 2000
@Composable
fun LandingScreen(onTimeOut:()->Unit,modifier: Modifier = Modifier){
    Box(
        modifier = modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){

        val onCurrentTimeout by rememberUpdatedState(onTimeOut)

        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            onCurrentTimeout()
        }

        Text(
            text = "Flight\nSchedule",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.W600
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LandingScreenPreview(){
    LandingScreen({}, modifier = Modifier)
}