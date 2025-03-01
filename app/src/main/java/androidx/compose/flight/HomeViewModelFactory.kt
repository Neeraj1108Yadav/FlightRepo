package androidx.compose.flight

import androidx.compose.flight.permission.LocationRequest
import androidx.compose.flight.permission.PermissionHandler
import androidx.compose.flight.screens.home.HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val permissionHandler: PermissionHandler,
    private val locationRequest: LocationRequest
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(permissionHandler, locationRequest ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}