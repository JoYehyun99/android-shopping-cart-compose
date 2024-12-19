package nextstep.shoppingcart.ui.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.DefaultCartRepository
import nextstep.shoppingcart.data.ProductRepository
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.cart.CartActivity
import nextstep.shoppingcart.ui.productdetail.DetailActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {
    private val cartProductStateHolder by lazy {
        CartProductStateHolder(
            cartRepository = DefaultCartRepository,
            productRepository = ProductRepository,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                ShoppingProductsScreen(
                    cartProducts = cartProductStateHolder.items,
                    navigateToDetail = ::navigateToDetail,
                    navigateToCart = ::navigateToCart,
                    onIncrease = { cartProductStateHolder.addOne(it) },
                    onDecrease = { cartProductStateHolder.removeOne(it) },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun navigateToDetail(productId: Long) {
        val intent = DetailActivity.intent(this@MainActivity, productId)
        startActivity(intent)
    }

    private fun navigateToCart() {
        startActivity(CartActivity.intent(this@MainActivity))
    }
}
