package nextstep.shoppingcart.ui.component

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.Product
import nextstep.shoppingcart.ProductRepository.products
import nextstep.signup.R

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    formatter: DecimalFormat = DecimalFormat(stringResource(R.string.currency_format)),
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
        )
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
    ProductItem(modifier = Modifier.padding(6.dp), product = products[0])
}
