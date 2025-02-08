package androidx.compose.flight.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home\nScreen",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.W600
        )
    }
}