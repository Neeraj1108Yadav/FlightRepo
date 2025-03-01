package androidx.compose.flight.permission

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

@Composable
fun LocationPermissionRequester(
    permissionHandler: PermissionHandler,
    onPermissionGranted : () -> Unit,
    onPermissionDenied : () -> Unit
){
    val isLocationPermissionAllowed by remember{ mutableStateOf(permissionHandler.isLocationPermissionGiven()) }

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
        if(!isLocationPermissionAllowed){
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }else{
            onPermissionGranted()
        }
    }
}