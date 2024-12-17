package nextstep.shoppingcart.data

import nextstep.shoppingcart.data.ProductRepository.products

data class Cart(
    val id: Long = 0L,
    val product: Product,
    val quantity: Int,
) {
    val totalPrice: Int get() = product.price * quantity
}

val dummyCartItems =
    listOf(
        Cart(0L, product = products[0], 1),
        Cart(1L, product = products[1], 2),
        Cart(2L, product = products[2], 3),
    )
