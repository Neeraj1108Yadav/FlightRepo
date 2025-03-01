package androidx.compose.flight.screens.home

import androidx.compose.flight.permission.LocationRequest
import androidx.compose.flight.permission.PermissionHandler
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

val MAX_PEOPLE = 4

class HomeViewModel(
   private val permissionHandler: PermissionHandler,
   private val locationRequest: LocationRequest
) : ViewModel() {

    var people by mutableStateOf(1)
        private set

    var isValidPeopleCount by mutableStateOf(false)
        private set

    private val _isLocationPermissionAllowed = MutableStateFlow(false)
    val isLocationPermissionAllowed: StateFlow<Boolean> = _isLocationPermissionAllowed.asStateFlow()

    private val _isGpsAllowed = MutableStateFlow(false)
    val isGpsAllowed: StateFlow<Boolean> = _isGpsAllowed.asStateFlow()

    val currentLocation: StateFlow<String?> = locationRequest.currentLocation

    fun addPeople(){
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateInputUserState()
    }

    private fun updateInputUserState(){
        isValidPeopleCount = people > MAX_PEOPLE
    }

    fun checkLocationPermissionStatus(){
        //Log.d("Location","Permission Granted = ${permissionHandler.isLocationPermissionGiven()}")
        _isLocationPermissionAllowed.value = permissionHandler.isLocationPermissionGiven()
    }

    fun checkGpsStatus(){
        //Log.d("Location","GPS Enabled = ${permissionHandler.isGpsEnabled()}")
        _isGpsAllowed.value = permissionHandler.isGpsEnabled()
    }

    fun requestLocation(){
        locationRequest.requestLocation()
    }
}