package androidx.compose.flight.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.flight.R
import androidx.compose.flight.components.UserInput
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlyScreenContent(
    viewModel: HomeViewModel
){
    ScreenContent{
        PeopleUserInput(
            titleSuffix = ", Economy",
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(8.dp))
        UserInput(
            text = "Current Location Input",
            vectorImageId = R.drawable.ic_location,
            onClick = {}
        )
    }
}

@Composable
private fun ScreenContent(content:@Composable () -> Unit){
    Column (
        Modifier.padding(start = 24.dp, top = 10.dp, end = 24.dp, bottom = 12.dp)
    ){
        content()
    }
}