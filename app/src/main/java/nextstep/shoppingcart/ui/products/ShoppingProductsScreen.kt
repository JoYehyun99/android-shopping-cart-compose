package nextstep.shoppingcart.ui.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.data.Product
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.component.ProductItem
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingProductsScreen(
    products: List<Product>,
    navigateToDetail: (id: Long) -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.product_list_title),
                        fontSize = 22.sp,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navigateToCart() },
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier.padding(6.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingProductsPreview() {
    ShoppingProductsScreen(
        products = products,
        {},
        {},
        modifier = Modifier.fillMaxSize(),
    )
}
