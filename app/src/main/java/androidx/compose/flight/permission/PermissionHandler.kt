package androidx.compose.flight.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

const val REQUEST_GPS_ENABLE = 1001

class PermissionHandler(private val context: Context){

    fun isLocationPermissionGiven() : Boolean{
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun isGpsEnabled():Boolean{
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun requestEnableGps(launcher: ActivityResultLauncher<IntentSenderRequest>,onEnable : () -> Unit){
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,1000).build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true)

        val settingsClient = LocationServices.getSettingsClient(context)
        val task = settingsClient.checkLocationSettings(builder.build())

        task.addOnSuccessListener{
            // GPS is already Enabled
            onEnable()
        }

        task.addOnFailureListener{exception ->
            if(exception is ResolvableApiException){
                try {
                    val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                    launcher.launch(intentSenderRequest) // Launch GPS enable dialog
                }catch (sendEx: IntentSender.SendIntentException){
                    sendEx.printStackTrace()
                }
            }
        }
    }
}