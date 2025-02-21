package androidx.compose.flight.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.flight.R
import androidx.compose.material3.Text

@Composable
fun SimpleUserInput(
    @DrawableRes vectorImageId: Int? = null,
    text:String
){
    UserInput(
        text = text,
        vectorImageId = vectorImageId
    )
}

@Composable
fun UserInput(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes vectorImageId:Int? = null,
    onClick : () -> Unit = {}
){
    BaseUserInput(
        modifier = modifier,
        vectorImageId = vectorImageId,
        onClick = onClick
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun BaseUserInput(
    modifier: Modifier = Modifier,
    @DrawableRes vectorImageId:Int? = null,
    onClick : () -> Unit = {},
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    content: @Composable () -> Unit
){
    Surface (
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(all = 12.dp),
        ) {
            if(vectorImageId != null){
                Icon(
                    modifier = Modifier.size(24.dp,24.dp),
                    painter = painterResource(vectorImageId),
                    contentDescription = null,
                    tint = tint
                )
                Spacer(Modifier.width(8.dp))
            }
            Row(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                content()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BaseUserInputPreview(){
    BaseUserInput(
        modifier = Modifier,
        vectorImageId = R.drawable.ic_restaurant
    ){
        Text(text = "text", style = MaterialTheme.typography.titleMedium)
    }
}