package androidx.compose.flight.permission

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.flight.screens.home.HomeViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun CheckAndPromptGps(
    viewModel: HomeViewModel,
    permissionHandler: PermissionHandler,
    onGpsEnabled : () -> Unit
){

    val gpsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {result ->
        if(result.resultCode == Activity.RESULT_OK){
            onGpsEnabled()
        }else{
            viewModel.updateGpsStatus(false)
        }
    }

    LaunchedEffect(Unit) {
        val gpsON = permissionHandler.isGpsEnabled()
        viewModel.updateGpsStatus(gpsON)
        delay(2000)
        viewModel.isGpsEnabled.collect{enabled ->
            if(enabled){
                onGpsEnabled()
            }else{
                permissionHandler.requestEnableGps(gpsLauncher) {
                    onGpsEnabled()
                }
            }
        }
    }
}