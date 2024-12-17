package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.ProductRepository
import nextstep.shoppingcart.ui.cart.CartActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class DetailActivity : ComponentActivity() {
    private val productId: Long by lazy { intent.getLongExtra(KEY_PRODUCT_ID, -1L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingCartTheme {
                ProductDetailScreen(
                    modifier = Modifier.fillMaxSize(),
                    product = ProductRepository.findProductById(productId),
                    navigateToCart = ::navigateToCart,
                    navigateBack = { finish() },
                )
            }
        }
    }

    private fun navigateToCart() {
        startActivity(CartActivity.intent(this@DetailActivity))
    }

    companion object {
        private const val KEY_PRODUCT_ID = "product_id"

        fun intent(
            context: Context,
            productId: Long,
        ): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(KEY_PRODUCT_ID, productId)
            }
    }
}
