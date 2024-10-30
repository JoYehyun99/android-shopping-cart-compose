package nextstep.shoppingcart

import nextstep.shoppingcart.ProductRepository.products

data class Cart(
    val id: Long,
    val product: Product,
    val quantity: Int,
)

val dummyCartItems =
    listOf(
        Cart(0L, product = products[0], 1),
        Cart(1L, product = products[1], 2),
        Cart(2L, product = products[2], 3),
    )
