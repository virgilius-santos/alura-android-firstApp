package com.virrub.orgs.productModel

import java.math.BigDecimal

class ProductsDAO {
    fun add(product: Product) {
        products.add(product)
    }

    fun findProducts() = products.toList()

    companion object {
        private val products = mutableListOf(
            Product(
                name = "name 1",
                description = "description",
                value = BigDecimal("19.99"),
                url = "https://static.vecteezy.com/ti/fotos-gratis/t2/2992336-laranjas-maduras-sobre-fundo-branco-gratis-foto.jpg"
            ),
            Product(
                name = "name 2",
                description = "description",
                value = BigDecimal("19.99"),
                url = "https://static.vecteezy.com/ti/fotos-gratis/t2/2992336-laranjas-maduras-sobre-fundo-branco-gratis-foto.jpg"
            ),
            Product(
                name = "name 3",
                description = "description",
                value = BigDecimal("19.99"),
                url = "https://static.vecteezy.com/ti/fotos-gratis/t2/2992336-laranjas-maduras-sobre-fundo-branco-gratis-foto.jpg"
            )
        )
    }
}