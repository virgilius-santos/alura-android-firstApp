package com.virrub.orgs.product.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

data class Product(
    var name: String,
    var description: String,
    var value: BigDecimal,
    var url: String?,
    var date: String?
)

fun Product.toTransition(): ProductTransition {
    return ProductTransition(
        name, description, value, url, date
    )
}

fun ProductTransition.toProduct(): Product {
    return Product(name, description, value, url, date)
}

@Parcelize
data class ProductTransition(
    var name: String,
    var description: String,
    var value: BigDecimal,
    var url: String?,
    var date: String?
) : Parcelable

@Entity
data class ProductEntity(
    var name: String,
    var description: String,
    var value: BigDecimal,
    var url: String?,
    var date: String?
)