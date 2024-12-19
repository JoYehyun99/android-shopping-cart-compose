package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun CounterButtonGroup(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onDecrement) {
            Icon(
                painter = painterResource(id = R.drawable.icon_remove),
                contentDescription = stringResource(R.string.minus_quantity),
            )
        }
        Text(
            text = "$count",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        IconButton(onClick = onIncrement) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.plus_quantity),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterButtonGroupPreview() {
    var number by remember { mutableIntStateOf(1) }
    CounterButtonGroup(count = number, onIncrement = { number++ }, onDecrement = { number-- })
}
