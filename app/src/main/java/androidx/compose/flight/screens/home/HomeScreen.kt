package androidx.compose.flight.screens.home

import androidx.compose.flight.components.TabBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


enum class HomeScreenTabs{
    Fly,Sleep,Eat
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
){

    var tabSelected by remember { mutableStateOf(HomeScreenTabs.Fly) }

    val configuration = LocalConfiguration.current
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

   BottomSheetScaffold(
       scaffoldState = sheetState,
       sheetPeekHeight = configuration.screenHeightDp.dp/3,
       topBar = {
           Column(
               modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
           ) {
               TabBar(
                   titles = HomeScreenTabs.values().map { it.name },
                   tabSelected = tabSelected
               ){selectedTab ->
                   tabSelected = selectedTab
               }
           }
       },
       sheetContent = {
           Box(
               modifier = modifier.fillMaxSize(),
               contentAlignment = Alignment.TopCenter
           ){
               Text(
                   text = "Bottom\nSheet",
                   style = MaterialTheme.typography.displayMedium,
                   fontWeight = FontWeight.W600
               )
           }
       },
   ) {
       Box(
           modifier = modifier.fillMaxSize()
               .background(color = MaterialTheme.colorScheme.primary),
       ){
           SearchContent(
               selectedTabs = tabSelected,
               viewModel = viewModel
           )
       }
   }
}

@Composable
private fun SearchContent(
    selectedTabs: HomeScreenTabs,
    viewModel: HomeViewModel
){
    when(selectedTabs){
        HomeScreenTabs.Fly -> {
            FlyScreenContent(viewModel)
        }
        HomeScreenTabs.Sleep -> {

        }
        HomeScreenTabs.Eat -> {

        }
    }
}
