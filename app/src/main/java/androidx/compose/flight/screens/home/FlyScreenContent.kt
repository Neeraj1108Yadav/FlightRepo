package androidx.compose.flight.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.flight.R
import androidx.compose.flight.components.UserInput
import androidx.compose.flight.permission.LocationRequest
import androidx.compose.flight.permission.PermissionHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlyScreenContent(
    viewModel: HomeViewModel,
    permissionHandler: PermissionHandler,
    requestLocation: LocationRequest
){
    ScreenContent{
        PeopleUserInput(
            titleSuffix = ", Economy",
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(8.dp))
        CurrentLocationInput(
            viewModel = viewModel,
            permissionHandler
        )
        Spacer(modifier = Modifier.height(8.dp))
        UserInput(
            text = "Destination Input",
            vectorImageId = R.drawable.ic_location,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        UserInput(
            text = "Travel Dates",
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