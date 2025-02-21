package androidx.compose.flight.screens.home

import androidx.compose.flight.components.UserInput
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.flight.R

class PeopleUserInputState{
    var people by mutableStateOf(1)
        private set

    fun addPerson(){
        people = (people % (MAX_PEOPLE + 1)) + 1
    }
}

@Composable
fun PeopleUserInput(
    titleSuffix:String? = "",
    peopleState: PeopleUserInputState = remember { PeopleUserInputState() }
){
    Column {
        val people = peopleState.people
        UserInput(
            text = if(people == 1) "$people Adult$titleSuffix" else "$people Adults$titleSuffix",
            vectorImageId = R.drawable.ic_person,
            onClick = {
                peopleState.addPerson()
            }
        )
    }
}