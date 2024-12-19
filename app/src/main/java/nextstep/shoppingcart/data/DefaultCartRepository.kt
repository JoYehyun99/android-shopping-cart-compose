package nextstep.shoppingcart.data

import nextstep.shoppingcart.data.ProductRepository.products

object DefaultCartRepository : CartRepository {
    private val carts =
        mutableListOf(
            Cart(0L, product = products[0], 1),
            Cart(1L, product = products[1], 2),
            Cart(2L, product = products[2], 3),
        )

    override fun getAllItems(): List<Cart> = carts.toList()

    override fun addOne(product: Product): List<Cart> {
        val item = carts.find { it.product == product }
        if (item == null) {
            carts.add(Cart(product = product, quantity = 1))
        } else {
            val index = carts.indexOf(item)
            carts[index] = item.copy(quantity = item.quantity + 1)
        }
        return carts.toList()
    }

    override fun removeOne(product: Product): List<Cart> {
        carts
            .find { it.product == product }
            ?.let { item ->
                if (item.quantity > 1) {
                    val index = carts.indexOf(item)
                    carts[index] = item.copy(quantity = item.quantity - 1)
                } else {
                    carts.remove(item)
                }
            }
        return carts.toList()
    }

    override fun removeAll(product: Product): List<Cart> {
        carts.removeAll { it.product == product }
        return carts.toList()
    }
}
