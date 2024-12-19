package nextstep.shoppingcart.ui.products

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
import nextstep.shoppingcart.data.CartProduct
import nextstep.shoppingcart.data.Product
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingProductsScreen(
    cartProducts: List<CartProduct>,
    navigateToDetail: (id: Long) -> Unit,
    navigateToCart: () -> Unit,
    onIncrease: (Product) -> Unit,
    onDecrease: (Product) -> Unit,
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
            items(cartProducts) { cartProducts ->
                ProductItem(
                    product = cartProducts.product,
                    quantity = cartProducts.quantity,
                    onIncrease = onIncrease,
                    onDecrease = onDecrease,
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
        cartProducts = cartProducts,
        {},
        {},
        {},
        {},
        modifier = Modifier.fillMaxSize(),
    )
}

private val cartProducts =
    listOf(
        CartProduct(
            product =
                Product(
                    id = 0L,
                    imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                    price = 2000,
                    name = "쿨피스 프리미엄 복숭아맛",
                ),
            quantity = 2,
        ),
        CartProduct(
            product =
                Product(
                    id = 1L,
                    imageUrl = "https://product-image.kurly.com/product/image/91e97eee-1d8a-4194-84de-19f6a90e69a2.jpg",
                    price = 13000,
                    name = "지수 머스크메론 2종",
                ),
            quantity = 3,
        ),
        CartProduct(
            product =
                Product(
                    id = 2L,
                    imageUrl = "https://product-image.kurly.com/product/image/ee17bd9e-1561-46af-a7bb-d9731361a243.jpg",
                    price = 9000,
                    name = "국산 블루베리 200g",
                ),
            quantity = 1,
        ),
        CartProduct(
            product =
                Product(
                    id = 3L,
                    imageUrl = "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=676/shop/data/goods/165303902534l0.jpg",
                    price = 4000,
                    name = "DOLE 실속 바나나 1kg",
                ),
            quantity = 4,
        ),
        CartProduct(
            product =
                Product(
                    id = 4L,
                    imageUrl = "https://product-image.kurly.com/cdn-cgi/image/quality=85,width=676/product/image/b573ba85-9bfa-433b-bafc-3356b081440b.jpg",
                    price = 13000,
                    name = "유명산지 고당도사과 1.5kg",
                ),
            quantity = 2,
        ),
        CartProduct(
            product =
                Product(
                    id = 5L,
                    imageUrl = "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=676/shop/data/goods/1653038449592l0.jpeg",
                    price = 12900,
                    name = "성주 참외 1.5kg(4~7입)",
                ),
            quantity = 3,
        ),
    )
