package nextstep.shoppingcart.ui.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.data.CartRepository
import nextstep.shoppingcart.data.Product

class CartStateHolder(
    private val repository: CartRepository,
) {
    private val _items = mutableStateListOf<Cart>()
    val items: List<Cart> get() = _items

    val totalPrice: Int get() = _items.sumOf { it.totalPrice }

    init {
        _items.addAll(repository.getAllItems())
    }

    private fun update(newValue: List<Cart>) {
        _items.clear()
        _items.addAll(newValue)
    }

    fun addOne(product: Product) {
        val result = repository.addOne(product)
        update(result)
    }

    fun removeOne(product: Product) {
        val result = repository.removeOne(product)
        update(result)
    }

    fun removeAll(product: Product) {
        val result = repository.removeAll(product)
        update(result)
    }
}
