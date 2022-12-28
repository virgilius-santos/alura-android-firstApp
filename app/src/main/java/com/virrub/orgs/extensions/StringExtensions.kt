package com.virrub.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

fun BigDecimal.currenceValue(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}

fun Long.toLocalDate(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.of("America/Sao_Paulo"))
        .withZoneSameInstant(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
        .toLocalDate()
        .toString()
}