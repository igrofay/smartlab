package com.example.smartlab.cart.model

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.Ratio

sealed class CartEvent {
    class IncrementNumber(val ratio: Ratio<CatalogEntryModel>): CartEvent()
    class DecrementNumber(val ratio: Ratio<CatalogEntryModel>): CartEvent()
    object ClearCart : CartEvent()
    class Delete(val catalogEntryModel: CatalogEntryModel): CartEvent()
}