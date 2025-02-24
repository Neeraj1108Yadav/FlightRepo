package androidx.compose.flight.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

val MAX_PEOPLE = 4

class HomeViewModel : ViewModel() {

    var people by mutableStateOf(1)
        private set

    var isValidPeopleCount by mutableStateOf(false)
        private set

    private val _isGpsEnabled = MutableSharedFlow<Boolean>()
    val isGpsEnabled: SharedFlow<Boolean> = _isGpsEnabled.asSharedFlow()


    fun addPeople(){
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateInputUserState()
    }

    private fun updateInputUserState(){
        isValidPeopleCount = people > MAX_PEOPLE
    }

    fun updateGpsStatus(isEnabled:Boolean){
        viewModelScope.launch{
            _isGpsEnabled.emit(isEnabled)
        }
    }

}