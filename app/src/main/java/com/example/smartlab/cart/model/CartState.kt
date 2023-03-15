package com.example.smartlab.cart.model

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.Ratio

data class CartState(
    val catalogCart: List<Ratio<CatalogEntryModel>> = listOf(),
    val sumCart: Int = 0,
)