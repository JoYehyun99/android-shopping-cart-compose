package nextstep.shoppingcart.ui.cart

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.component.BackNavigationAppBar
import nextstep.shoppingcart.ui.component.BottomButton
import nextstep.shoppingcart.ui.component.CartItem
import nextstep.signup.R

@Composable
fun CartScreen(
    navigateBack: () -> Unit,
    cartItems: List<Cart>,
    totalPrice: Int,
    onIncrease: (Long) -> Unit,
    onDecrease: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    modifier: Modifier = Modifier,
    formatter: DecimalFormat = DecimalFormat(stringResource(R.string.currency_format)),
) {
    Scaffold(
        modifier = modifier,
        topBar = { BackNavigationAppBar(stringResource(R.string.cart_title), navigateBack) },
    ) { paddingValue ->
        Column {
            LazyColumn(
                modifier = Modifier.padding(paddingValue).weight(1f),
                contentPadding = PaddingValues(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(cartItems) { item ->
                    CartItem(cart = item, onIncrease = onIncrease, onDecrease = onDecrease, onDelete = onDelete)
                }
            }
            BottomButton(title = "주문하기(${formatter.format(totalPrice)})") { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val dummyCartItems =
        remember {
            mutableStateListOf(
                Cart(0L, product = products[0], 1),
                Cart(1L, product = products[1], 2),
                Cart(2L, product = products[2], 3),
            )
        }

    CartScreen(
        navigateBack = {},
        cartItems = dummyCartItems,
        totalPrice =
            dummyCartItems.sumOf {
                it.quantity *
                    it.product.price
            },
        onIncrease = {},
        onDecrease = {},
        onDelete = {},
        modifier = Modifier.fillMaxSize(),
    )
}
