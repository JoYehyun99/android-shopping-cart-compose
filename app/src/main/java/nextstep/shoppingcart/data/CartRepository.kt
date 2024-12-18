package nextstep.shoppingcart.data

import nextstep.shoppingcart.data.ProductRepository.products

object CartRepository {
    private val _items =
        mutableListOf(
            Cart(0L, product = products[0], 1),
            Cart(1L, product = products[1], 2),
            Cart(2L, product = products[2], 3),
        )
    val items: List<Cart> get() = _items.toList()

    fun addOne(cart: Cart): List<Cart> {
        val item = _items.find { it.product == cart.product }
        if (item == null) {
            _items.add(Cart(product = cart.product, quantity = 1))
        } else {
            val index = _items.indexOf(item)
            _items[index] = item.copy(quantity = item.quantity + 1)
        }
        return items
    }

    fun removeOne(cart: Cart): List<Cart> {
        _items
            .find { it.product == cart.product }
            ?.let { item ->
                if (item.quantity > 1) {
                    val index = _items.indexOf(item)
                    _items[index] = item.copy(quantity = item.quantity - 1)
                } else {
                    _items.remove(item)
                }
            }
        return items
    }

    fun removeAll(cart: Cart): List<Cart> {
        _items.removeAll { it.product == cart.product }
        return items
    }
}
