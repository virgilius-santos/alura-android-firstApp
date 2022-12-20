package com.virrub.orgs.product.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

data class Product(
    var name: String,
    var description: String,
    var value: BigDecimal,
    var url: String?
)

fun Product.toTransition(): ProductTransition {
    return ProductTransition(
        name, description, value, url
    )
}

fun ProductTransition.toProduct(): Product? {
    val name = name?.let { it } ?: return null
    val description = description?.let { it } ?: return null
    return Product(name, description, value, url)
}

@Parcelize
data class ProductTransition(
    var name: String?,
    var description: String?,
    var value: BigDecimal,
    var url: String?
) : Parcelable