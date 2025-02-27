package androidx.compose.flight.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class LocationRequest(private val context: Context) {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    private var _currentLocation = MutableStateFlow<String?>(null)
    val currentLocation: StateFlow<String?> = _currentLocation.asStateFlow()

    fun requestLocation(){
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,1000).build()

        val locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let {location ->
                    _currentLocation.value = getLocationAddress(location.latitude,location.longitude)
                    fusedLocationProviderClient.removeLocationUpdates(this)
                }
            }
        }

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,
                Looper.getMainLooper())
        }
    }

    private fun getLocationAddress(latitude:Double,longitude:Double):String?{
        val geocoder = Geocoder(context, Locale.getDefault())
        val address = geocoder.getFromLocation(latitude,longitude,1)
        return "${address?.firstOrNull()?.locality}, ${address?.firstOrNull()?.countryName}"
    }
}