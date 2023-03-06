package com.example.data.domain.model.catalog

@kotlinx.serialization.Serializable
data class Ratio<T>(
    val product : T,
    val amount: Int = 1,
)