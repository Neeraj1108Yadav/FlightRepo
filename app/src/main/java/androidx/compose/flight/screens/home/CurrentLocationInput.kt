package androidx.compose.flight.screens.home

import android.util.Log
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun CurrentLocationInput(
    viewModel: HomeViewModel,
    permissionHandler: PermissionHandler
){
    var showPermissionDialog by remember {mutableStateOf(false)}
    var showGpsDialog by remember {mutableStateOf(false)}
    val isPermissionGranted by viewModel.isLocationPermissionAllowed.collectAsState()
    val isGpsEnabled by viewModel.isGpsAllowed.collectAsState()
    val currentLocation by viewModel.currentLocation.collectAsState()

    LaunchedEffect(Unit) {
        launch{
            viewModel.checkGpsStatus()
        }
        viewModel.checkLocationPermissionStatus()
    }


    UserInput(
        text = currentLocation ?: "Your Current Location",
        vectorImageId = R.drawable.ic_location,
        onClick = {
            when{
                !isPermissionGranted -> showPermissionDialog = true
                !isGpsEnabled -> showGpsDialog = true
                else ->  viewModel.requestLocation()
            }
        }
    )

    if(showPermissionDialog){
        LocationPermissionRequester(
            permissionHandler,
            onPermissionGranted = {
                showPermissionDialog = false
               if(!isGpsEnabled){
                   showGpsDialog = true
               }else{
                   viewModel.requestLocation()
               }
            },
            onPermissionDenied = {

            }
        )
    }

    if(showGpsDialog){
        CheckAndPromptGps(permissionHandler,
            onGpsEnabled = {
                showGpsDialog = false
                viewModel.requestLocation()
            },
            onGpsEnableDenied = {

            }
        )
    }
}