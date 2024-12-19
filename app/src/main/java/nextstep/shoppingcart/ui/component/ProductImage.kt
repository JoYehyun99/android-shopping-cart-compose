package nextstep.shoppingcart.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

/**
 * 이미지를 로딩하는 기능을 담당하는 컴포저블 함수
 *
 * 내부적으로 어떤 이미지 로딩 라이브러리를 사용하는지 감추고 있기 때문에
 * 라이브러리 변경에 따른 영향을 최소화할 수 있다.
 *
 * 각각 다른 비율과 크기를 가지는 이미지를 로딩해야 할 경우에는
 * 외부에서 Modifier를 사용하여 조절 가능하다.
 */

@Composable
fun ProductImage(
    imageUrl: String,
    modifier: Modifier,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
