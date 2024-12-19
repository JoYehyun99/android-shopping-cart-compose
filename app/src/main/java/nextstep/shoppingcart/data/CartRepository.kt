package nextstep.shoppingcart.data

interface CartRepository {
    fun getAllItems(): List<Cart>

    fun addOne(product: Product): List<Cart>

    fun removeOne(product: Product): List<Cart>

    fun removeAll(product: Product): List<Cart>
}
