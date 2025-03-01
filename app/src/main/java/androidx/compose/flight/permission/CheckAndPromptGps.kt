package androidx.compose.flight.permission

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.flight.screens.home.HomeViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CheckAndPromptGps(
    permissionHandler: PermissionHandler,
    onGpsEnabled : () -> Unit,
    onGpsEnableDenied : () -> Unit
){

    val gpsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {result ->
        if(result.resultCode == Activity.RESULT_OK){
            onGpsEnabled()
        }else{
           onGpsEnableDenied()
        }
    }

    LaunchedEffect(Unit) {
        permissionHandler.requestEnableGps(gpsLauncher) {
            onGpsEnabled()
        }
    }
}