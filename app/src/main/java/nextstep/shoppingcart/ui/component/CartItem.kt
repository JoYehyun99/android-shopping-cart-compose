package nextstep.shoppingcart.ui.component

import android.icu.text.DecimalFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.Product
import nextstep.shoppingcart.ProductRepository.products
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.signup.R

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    product: Product,
    quantity: Int,
    formatter: DecimalFormat = DecimalFormat(stringResource(R.string.currency_format)),
) {
    OutlinedCard(
        modifier = modifier,
        border = BorderStroke(1.dp, color = Gray10),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp).padding(bottom = 18.dp, top = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 136.dp, height = 84.dp),
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(formatter.format(product.price), fontSize = 16.sp)
                    CounterButtonGroup(count = quantity)
                }
            }
        }
    }
}

@Composable
fun CounterButtonGroup(
    modifier: Modifier = Modifier,
    count: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.icon_remove),
                contentDescription = null,
            )
        }
        Text(
            "$count",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    CartItem(modifier = Modifier.fillMaxWidth(), products[0], 1)
}

@Preview(showBackground = true)
@Composable
fun CounterButtonGroupPreview() {
    CounterButtonGroup(count = 1)
}
