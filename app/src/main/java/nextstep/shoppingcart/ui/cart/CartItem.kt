package nextstep.shoppingcart.ui.cart

import android.icu.text.DecimalFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.data.Product
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.component.CounterButtonGroup
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.signup.R

@Composable
fun CartItem(
    cart: Cart,
    onIncrease: (Product) -> Unit,
    onDecrease: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    modifier: Modifier = Modifier,
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
                Text(cart.product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = { onDelete(cart.product) }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.remove_shopping_cart_item),
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = cart.product.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 136.dp, height = 84.dp),
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(formatter.format(cart.product.price), fontSize = 16.sp)
                    CounterButtonGroup(
                        count = cart.quantity,
                        onIncrement = { onIncrease(cart.product) },
                        onDecrement = { onDecrease(cart.product) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    CartItem(Cart(0L, product = products[0], 1), {}, {}, {}, modifier = Modifier.fillMaxWidth())
}
