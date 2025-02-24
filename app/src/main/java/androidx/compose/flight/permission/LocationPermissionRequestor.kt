package androidx.compose.flight.permission

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.delay

@Composable
fun LocationPermissionRequester(
    permissionHandler: PermissionHandler,
    onPermissionGranted : () -> Unit,
    onPermissionDenied : () -> Unit
){
    var shouldRequestLocationPermission = rememberSaveable { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if(isGranted){
            onPermissionGranted()
        }else{
            onPermissionDenied()
        }
    }

    LaunchedEffect(Unit) {
        if(!permissionHandler.isLocationPermissionGiven()){
            shouldRequestLocationPermission.value = true
        }
    }

    LaunchedEffect(shouldRequestLocationPermission.value) {
        delay(1000)
        if(shouldRequestLocationPermission.value){
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
}