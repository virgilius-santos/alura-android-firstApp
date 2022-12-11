package com.virrub.orgs

import com.virrub.orgs.productList.Product
import java.math.BigDecimal

class ProductsDAO {
    fun add(product: Product) {
        products.add(product)
    }

    fun findProducts() = products.toList()

    companion object {
        private val products = mutableListOf(
            Product(name = "name 1", description = "description", value = BigDecimal("19.99")),
            Product(name = "name 2", description = "description", value = BigDecimal("19.99")),
            Product(name = "name 3", description = "description", value = BigDecimal("19.99"))
        )
    }
}