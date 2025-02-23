package androidx.compose.flight.permission

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun LocationPermissionRequester(
    onPermissionGranted : () -> Unit,
    onPermissionDenied : () -> Unit
){
    val context = LocalContext.current
    val activity = context as Activity
    val permissionHandler = remember { PermissionHandler(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {permissions->
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if(isGranted){
            onPermissionGranted()
        }else{
            onPermissionDenied()
        }
    }

    if(permissionHandler.isLocationPermissionGiven()){
        onPermissionGranted()
    }else{
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}