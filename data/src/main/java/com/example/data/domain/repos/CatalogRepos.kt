package com.example.data.domain.repos

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.PromotionModel

interface CatalogRepos {
    suspend fun getListPromotion(): List<PromotionModel>
    suspend fun getListCatalogEntry(): List<CatalogEntryModel>
}