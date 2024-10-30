package nextstep.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {

            }
        }
    }
}

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { BackNavigationAppBar(stringResource(R.string.cart_title), {}) },
    ) { innerPadding ->
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(modifier = Modifier.fillMaxSize())
}
