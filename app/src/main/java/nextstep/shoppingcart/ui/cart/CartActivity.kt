package nextstep.shoppingcart.ui.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dummyCartItems =
            mutableStateListOf(
                Cart(0L, product = products[0], 1),
                Cart(1L, product = products[1], 2),
                Cart(2L, product = products[2], 3),
            )

        setContent {
            ShoppingCartTheme {
                CartScreen(
                    navigateBack = { finish() },
                    cartItems = dummyCartItems,
                    totalPrice =
                        dummyCartItems.sumOf {
                            it.quantity *
                                it.product.price
                        },
                    onIncrease = { id ->
                        val index = dummyCartItems.indexOfFirst { id == it.id }
                        if (index != -1) {
                            dummyCartItems[index] = dummyCartItems[index].copy(quantity = dummyCartItems[index].quantity + 1)
                        }
                    },
                    onDecrease = { id ->
                        val index = dummyCartItems.indexOfFirst { id == it.id }
                        if (index != -1) {
                            if (dummyCartItems[index].quantity == 1) {
                                dummyCartItems.remove(dummyCartItems[index])
                                return@CartScreen
                            }
                            dummyCartItems[index] = dummyCartItems[index].copy(quantity = dummyCartItems[index].quantity - 1)
                        }
                    },
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(WindowInsets.navigationBars.asPaddingValues()),
                )
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}
