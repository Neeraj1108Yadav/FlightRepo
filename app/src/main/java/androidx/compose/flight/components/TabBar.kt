package androidx.compose.flight.components

import androidx.compose.flight.screens.HomeScreenTabs
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat

@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: HomeScreenTabs
){
    TabRow(
        modifier = modifier,
        selectedTabIndex = tabSelected.ordinal,
        indicator =  {},
        divider = {}
    ) {
        titles.forEachIndexed {index,title ->
            val selected = index == tabSelected.ordinal
            var textModifier = Modifier.padding(12.dp)
            if(selected){
                textModifier = Modifier
                    .border(BorderStroke(2.dp,Color.White), RoundedCornerShape(16.dp))
                    .then(textModifier)
            }

            Tab(
                modifier = Modifier.padding(5.dp),
                selected = selected,
                onClick = {}
            ) {
                Text(
                    modifier = textModifier,
                    text = title.uppercase(ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!)
                )
            }
        }
    }
}