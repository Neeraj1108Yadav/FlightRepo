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
        launch{
            viewModel.isGpsEnabled.collect{enabled ->
                Log.d("GPS","Is GPS Enabled$$ : $enabled")
                if(enabled){
                    onGpsEnabled()
                }else{
                    permissionHandler.requestEnableGps(gpsLauncher) {
                        onGpsEnabled()
                    }
                }
            }
        }
        viewModel.updateGpsStatus(permissionHandler.isGpsEnabled())

    }
}