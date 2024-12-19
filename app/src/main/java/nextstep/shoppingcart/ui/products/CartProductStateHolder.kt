package nextstep.shoppingcart.ui.products

import androidx.compose.runtime.mutableStateListOf
import nextstep.shoppingcart.data.CartProduct
import nextstep.shoppingcart.data.CartRepository
import nextstep.shoppingcart.data.Product
import nextstep.shoppingcart.data.ProductRepository

class CartProductStateHolder(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) {
    private val _items = mutableStateListOf<CartProduct>()
    val items: List<CartProduct> get() = _items

    init {
        val cartItems = cartRepository.getAllItems()
        val products = productRepository.products
        val cartProduct =
            products.map {
                val cartItem = cartItems.find { it.product.id == it.id }
                CartProduct(
                    product = it,
                    quantity = cartItem?.quantity ?: 0,
                )
            }
        _items.addAll(cartProduct)
    }

    fun addOne(product: Product) {
        cartRepository.addOne(product)
        val existingProduct = _items.find { it.product.id == product.id }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(quantity = existingProduct.quantity + 1)
            _items[_items.indexOf(existingProduct)] = updatedProduct
        } else {
            _items.add(CartProduct(product = product, quantity = 1))
        }
    }

    fun removeOne(product: Product) {
        cartRepository.removeOne(product)
        val existingProduct = _items.find { it.product.id == product.id }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(quantity = existingProduct.quantity - 1)
            _items[_items.indexOf(existingProduct)] = updatedProduct
        }
    }
}
