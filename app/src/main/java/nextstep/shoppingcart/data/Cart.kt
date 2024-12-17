package nextstep.shoppingcart.data

data class Cart(
    val id: Long = 0L,
    val product: Product,
    val quantity: Int,
) {
    val totalPrice: Int get() = product.price * quantity
}
