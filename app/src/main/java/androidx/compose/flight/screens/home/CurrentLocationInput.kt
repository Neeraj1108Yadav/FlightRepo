package androidx.compose.flight.screens.home

import androidx.compose.flight.R
import androidx.compose.flight.components.UserInput
import androidx.compose.flight.permission.CheckAndPromptGps
import androidx.compose.flight.permission.LocationPermissionRequester
import androidx.compose.flight.permission.PermissionHandler
import androidx.compose.runtime.Composable
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
    var currentLocation = rememberSaveable { mutableStateOf("Current Location Input") }
    var isPermissionChecked by rememberSaveable { mutableStateOf(false) }
    var isGpsChecked by rememberSaveable { mutableStateOf(false) }
    val permissionHandler = remember { PermissionHandler(context) }

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

        }
    }
}