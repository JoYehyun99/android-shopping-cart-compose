package nextstep.shoppingcart.ui.products

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.data.Product
import nextstep.shoppingcart.data.ProductRepository.products
import nextstep.shoppingcart.ui.component.CounterButtonGroup
import nextstep.signup.R

@Composable
fun ProductItem(
    product: Product,
    quantity: Int,
    onIncrease: (Product) -> Unit,
    onDecrease: (Product) -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    formatter: DecimalFormat = DecimalFormat(stringResource(R.string.currency_format)),
) {
    Column(modifier = modifier.clickable { navigateToDetail(product.id) }) {
        Box {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
            )
            if (quantity > 0) {
                CounterButtonGroup(
                    count = quantity,
                    onIncrement = { onIncrease(product) },
                    onDecrement = { onDecrease(product) },
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = (-12).dp),
                )
            } else {
                IconButton(
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-12).dp)
                            .background(color = Color.White, shape = CircleShape),
                    onClick = { onIncrease(product) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.plus_quantity),
                    )
                }
            }
        }
        Text(
            product.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(formatter.format(product.price), fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = product,
        quantity = 0,
        onIncrease = {},
        onDecrease = {},
        navigateToDetail = {},
        modifier = Modifier.padding(6.dp),
    )
}

private val product =
    Product(
        id = 0L,
        imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
        price = 2000,
        name = "쿨피스 프리미엄 복숭아맛",
    )
