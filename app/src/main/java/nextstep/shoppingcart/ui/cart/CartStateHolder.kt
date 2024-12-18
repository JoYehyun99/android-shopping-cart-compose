package nextstep.shoppingcart.ui.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.data.CartRepository

class CartStateHolder(
    private val repository: CartRepository,
) {
    private val _items = mutableStateListOf<Cart>()
    val items: List<Cart> get() = _items

    val totalPrice: Int get() = _items.sumOf { it.totalPrice }

    init {
        _items.addAll(repository.items)
    }

    private fun update(newValue: List<Cart>) {
        _items.clear()
        _items.addAll(newValue)
    }

    fun addOne(cart: Cart) {
        val result = repository.addOne(cart)
        update(result)
    }

    fun removeOne(cart: Cart) {
        val result = repository.removeOne(cart)
        update(result)
    }

    fun removeAll(cart: Cart) {
        val result = repository.removeAll(cart)
        update(result)
    }
}
