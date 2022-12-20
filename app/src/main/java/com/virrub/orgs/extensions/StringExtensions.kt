package com.virrub.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.currenceValue(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}