package androidx.compose.flight.screens.home

import androidx.compose.flight.R
import androidx.compose.flight.components.UserInput
import androidx.compose.flight.permission.LocationPermissionRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun CurrentLocationInput(){
    var currentLocation = rememberSaveable { mutableStateOf("Current Location Input") }
    var isPermissionChecked by rememberSaveable { mutableStateOf(false) }

    UserInput(
        text = currentLocation.value,
        vectorImageId = R.drawable.ic_location,
        onClick = {
            isPermissionChecked = true
        }
    )

    if(isPermissionChecked){
        LocationPermissionRequester(
            onPermissionGranted = {

            },
            onPermissionDenied = {

            }
        )
    }
}