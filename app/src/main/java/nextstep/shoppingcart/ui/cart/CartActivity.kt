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
import nextstep.shoppingcart.data.DefaultCartRepository
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class CartActivity : ComponentActivity() {
    private val cartItems by lazy { CartStateHolder(DefaultCartRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingCartTheme {
                CartScreen(
                    cartItems = cartItems.items,
                    totalPrice = cartItems.totalPrice,
                    navigateBack = { finish() },
                    onIncrease = { product -> cartItems.addOne(product) },
                    onDecrease = { product -> cartItems.removeOne(product) },
                    onDelete = { product -> cartItems.removeAll(product) },
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
