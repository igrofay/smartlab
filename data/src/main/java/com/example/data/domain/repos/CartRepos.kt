package com.example.data.domain.repos

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.Ratio
import kotlinx.coroutines.flow.Flow

interface CartRepos {
    fun getCart(): Flow<List<Ratio<CatalogEntryModel>>>
    suspend fun add(ratio: Ratio<CatalogEntryModel>)
    suspend fun update(ratio: Ratio<CatalogEntryModel>)
    suspend fun remove(entryModel: CatalogEntryModel)
}