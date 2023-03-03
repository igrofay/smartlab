package com.example.smartlab.analyzes.model

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.PromotionModel

internal sealed class AnalyzesState {
    object Load: AnalyzesState()
    data class Success(
        val catalog: Map<String, List<CatalogEntryModel>>,
        val promotions: List<PromotionModel>
    ): AnalyzesState()
}