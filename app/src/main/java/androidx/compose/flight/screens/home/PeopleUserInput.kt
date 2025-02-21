package androidx.compose.flight.screens.home

import androidx.compose.flight.components.UserInput
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.flight.R

@Composable
fun PeopleUserInput(
    titleSuffix:String? = "",
    viewModel: HomeViewModel
){
    Column {
        val people = viewModel.people
        UserInput(
            text = if(people == 1) "$people Adult$titleSuffix" else "$people Adults$titleSuffix",
            vectorImageId = R.drawable.ic_person,
            onClick = {
                viewModel.addPeople()
            }
        )
    }
}