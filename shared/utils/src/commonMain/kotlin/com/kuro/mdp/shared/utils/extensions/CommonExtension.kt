package com.kuro.mdp.shared.utils.extensions

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


fun <T> List<List<T>>.extractAllItem() = mutableListOf<T>().apply {
    this@extractAllItem.forEach { addAll(it) }
}

fun Int?.toStringOrEmpty() = this?.toString() ?: ""

@OptIn(ExperimentalUuidApi::class)
fun generateUniqueKey() = Uuid.random().toLongs { mostSignificantBits, _ ->
    mostSignificantBits and Long.MAX_VALUE
}