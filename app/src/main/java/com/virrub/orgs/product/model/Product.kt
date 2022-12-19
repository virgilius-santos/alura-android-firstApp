package com.virrub.orgs.product.model

import java.math.BigDecimal

data class Product(
    var name: String,
    var description: String,
    var value: BigDecimal,
    var url: String?
)
