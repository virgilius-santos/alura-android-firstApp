package com.virrub.orgs.product.model

import android.os.Parcel
import android.os.Parcelable
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

data class ProductTransition(
    var name: String?,
    var description: String?,
    var value: BigDecimal,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        BigDecimal(parcel.readString()),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(value.toString())
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductTransition> {
        override fun createFromParcel(parcel: Parcel): ProductTransition {
            return ProductTransition(parcel)
        }

        override fun newArray(size: Int): Array<ProductTransition?> {
            return arrayOfNulls(size)
        }
    }
}