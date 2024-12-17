package nextstep.shoppingcart.data

object CartRepository {
    private val _items: MutableList<Cart> = mutableListOf()
    val items: List<Cart> get() = _items.toList()

    val totalPrice: Int get() = _items.sumOf { it.totalPrice }

    fun addOne(product: Product): List<Cart> {
        val item = _items.find { it.product == product }
        if (item == null) {
            _items.add(Cart(product = product, quantity = 1))
        } else {
            val index = _items.indexOf(item)
            _items[index] = item.copy(quantity = item.quantity + 1)
        }
        return items
    }

    fun removeOne(product: Product): List<Cart> {
        _items
            .find { it.product == product }
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

    fun removeAll(product: Product): List<Cart> {
        _items.removeAll { it.product == product }
        return items
    }
}
