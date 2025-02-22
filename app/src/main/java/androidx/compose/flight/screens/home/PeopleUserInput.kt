package androidx.compose.flight.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.flight.components.UserInput
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.flight.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun PeopleUserInput(
    titleSuffix:String? = "",
    viewModel: HomeViewModel
){
    Column {
        val people = viewModel.people
        val isValidPeople = viewModel.isValidPeopleCount
        val transitionState = remember { MutableTransitionState(false) }

        LaunchedEffect(isValidPeople) {
            transitionState.targetState = isValidPeople
        }

        UserInput(
            text = if(people == 1) "$people Adult$titleSuffix" else "$people Adults$titleSuffix",
            vectorImageId = R.drawable.ic_person,
            onClick = {
                viewModel.addPeople()
            }
        )
        AnimatedVisibility(
            visibleState = transitionState,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Error: We don't support more than $MAX_PEOPLE people",
                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.errorContainer)
            )
        }
    }
}