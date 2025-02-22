package androidx.compose.flight.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

val MAX_PEOPLE = 4

class HomeViewModel : ViewModel() {

    var people by mutableStateOf(1)
        private set

    var isValidPeopleCount by mutableStateOf(false)
        private set


    fun addPeople(){
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateInputUserState()
    }

    private fun updateInputUserState(){
        isValidPeopleCount = people > MAX_PEOPLE
    }

}