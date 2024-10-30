package nextstep.shoppingcart

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.ProductRepository.findProductById
import nextstep.shoppingcart.ProductRepository.products
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

class DetailActivity : ComponentActivity() {
    private val productId: Long by lazy { intent.getLongExtra(KEY_PRODUCT_ID, -1L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingCartTheme {
                ProductDetailScreen(
                    modifier = Modifier.fillMaxSize(),
                    product = findProductById(productId),
                )
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavigationAppBar(
    title: String,
    onBackClick: () -> Unit,
)  {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(12.dp).clickable { onBackClick() },
            )
        },
    )
}

@Composable
private fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    product: Product,
    formatter: DecimalFormat = DecimalFormat(stringResource(R.string.currency_format)),
) {
    Scaffold(
        modifier = modifier,
        topBar = { BackNavigationAppBar(stringResource(R.string.product_detail_title), {}) },
    ) { paddingValue ->
        Column(
            modifier = Modifier.padding(paddingValue).fillMaxSize(),
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            )
            Text(
                product.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(18.dp),
            )
            HorizontalDivider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(stringResource(R.string.product_price_title), fontSize = 20.sp)
                Text(formatter.format(product.price), fontSize = 20.sp)
            }
            Spacer(Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Blue50,
                        contentColor = Color.White,
                    ),
                contentPadding = PaddingValues(vertical = 15.dp),
                shape = RectangleShape,
                onClick = {
                    // TODO: 클릭 액션
                },
            ) {
                Text(
                    stringResource(R.string.add_cart_button_text),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailPreview() {
    ProductDetailScreen(product = products[0])
}
