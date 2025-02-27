package androidx.compose.flight.screens.home

import androidx.compose.flight.R
import androidx.compose.flight.components.UserInput
import androidx.compose.flight.permission.CheckAndPromptGps
import androidx.compose.flight.permission.LocationPermissionRequester
import androidx.compose.flight.permission.LocationRequest
import androidx.compose.flight.permission.PermissionHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun CurrentLocationInput(
    viewModel: HomeViewModel
){
    val context = LocalContext.current
    val locationRequest = remember { LocationRequest(context) }
    var isPermissionChecked by rememberSaveable { mutableStateOf(false) }
    var isGpsChecked by rememberSaveable { mutableStateOf(false) }
    val permissionHandler = remember { PermissionHandler(context) }
    var currentLocation = rememberSaveable{ mutableStateOf("Your Current Location") }

    LaunchedEffect(Unit) {
        locationRequest.currentLocation.collect{location ->
            location?.let {
                currentLocation.value = it
            }
        }
    }

    UserInput(
        text = currentLocation.value,
        vectorImageId = R.drawable.ic_location,
        onClick = {
            isPermissionChecked = true
        }
    )

    if(isPermissionChecked){
        LocationPermissionRequester(
            permissionHandler,
            onPermissionGranted = {
                isGpsChecked = true
            },
            onPermissionDenied = {

            }
        )
    }

    if(isGpsChecked){
        CheckAndPromptGps(viewModel,permissionHandler) {
            locationRequest.requestLocation()
        }
    }
}