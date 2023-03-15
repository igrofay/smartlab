package com.example.data.domain.repos

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.Ratio
import kotlinx.coroutines.flow.Flow

interface CartRepos {
    fun getCartCatalog(): Flow<List<Ratio<CatalogEntryModel>>>
    suspend fun addCatalogEntry(ratio: Ratio<CatalogEntryModel>)
    suspend fun updateCatalogEntry(ratio: Ratio<CatalogEntryModel>)
    suspend fun removeCatalogEntry(entryModel: CatalogEntryModel)
    suspend fun clearCatalogEntry()
}