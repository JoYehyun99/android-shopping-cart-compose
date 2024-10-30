package nextstep.shoppingcart

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.ui.component.BackNavigationAppBar
import nextstep.shoppingcart.ui.component.BottomButton
import nextstep.shoppingcart.ui.component.CartItem
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingCartTheme {
                CartScreen(
                    modifier = Modifier.fillMaxSize().padding(WindowInsets.navigationBars.asPaddingValues()),
                    navigateBack = { finish() },
                    cartItems = dummyCartItems,
                    totalPrice =
                    dummyCartItems.sumOf {
                        it.quantity *
                                it.product.price
                    },
                )
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    cartItems: List<Cart>,
    totalPrice: Int,
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
                    CartItem(product = item.product, quantity = item.quantity)
                }
            }
            BottomButton(title = "주문하기(${formatter.format(totalPrice)})") { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(
        modifier = Modifier.fillMaxSize(),
        navigateBack = {},
        cartItems = dummyCartItems,
        totalPrice =
            dummyCartItems.sumOf {
                it.quantity *
                    it.product.price
            },
    )
}
