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
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.dummyCartItems
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingCartTheme {
                CartScreen(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(WindowInsets.navigationBars.asPaddingValues()),
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
