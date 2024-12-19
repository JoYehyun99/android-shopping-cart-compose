package nextstep.shoppingcart.ui.cart

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.data.Product
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartScreenTest {
    private val stateHolder = CartStateHolder(FakeCartRepository())

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CartScreen(
                cartItems = stateHolder.items,
                navigateBack = {},
                totalPrice = stateHolder.totalPrice,
                onIncrease = { stateHolder.addOne(it) },
                onDecrease = { stateHolder.removeOne(it) },
                onDelete = { stateHolder.removeAll(it) },
            )
        }
    }

    @Test
    fun `담긴_상품_가격의_총합이_노출된다`() {
        val product =
            Product(
                id = 0L,
                imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                price = 2000,
                name = "쿨피스 프리미엄 복숭아맛",
            )
        stateHolder.addOne(product)

        composeTestRule
            .onNodeWithText("주문하기(2,000원)")
            .assertExists()
    }

    @Test
    fun `담긴_상품을_제거할_수_있다`() {
        // given
        val product =
            Product(
                id = 0L,
                imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                price = 2000,
                name = "쿨피스 프리미엄 복숭아맛",
            )
        stateHolder.addOne(product)

        // when
        composeTestRule
            .onNodeWithContentDescription("장바구니 상품 삭제")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("쿨피스 프리미엄 복숭아맛")
            .assertDoesNotExist()
    }

    @Test
    fun `담긴_상품의_수량을_증가시키면_상품_가격에_반영된다`() {
        // given
        val product =
            Product(
                id = 0L,
                imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                price = 2000,
                name = "쿨피스 프리미엄 복숭아맛",
            )
        stateHolder.addOne(product)

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 증가")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("주문하기(4,000원)")
            .assertExists()
    }

    @Test
    fun `담긴_상품의_수량을_감소시키면_상품_가격에_반영된다`() {
        // given
        val product =
            Product(
                id = 0L,
                imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                price = 2000,
                name = "쿨피스 프리미엄 복숭아맛",
            )
        stateHolder.addOne(product)
        stateHolder.addOne(product)

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 감소")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("주문하기(2,000원)")
            .assertExists()
    }

    @Test
    fun `담긴_상품의_수량을_1보다_적게_하면_상품이_삭제된다`() {
        // given
        val product =
            Product(
                id = 0L,
                imageUrl = "https://product-image.kurly.com/product/image/0a8fe9ec-2ee0-495e-a6fc-b25de98e2d09.jpg",
                price = 2000,
                name = "쿨피스 프리미엄 복숭아맛",
            )
        stateHolder.addOne(product)

        // when
        composeTestRule
            .onNodeWithContentDescription("수량 감소")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("쿨피스 프리미엄 복숭아맛")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("주문하기(0원)")
            .assertExists()
    }
}
