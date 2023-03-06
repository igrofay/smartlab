package com.example.smartlab.analyzes.model

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.PromotionModel

internal sealed class AnalyzesState {
    object Load: AnalyzesState()
    data class Success(
        val catalog: Map<String, List<CatalogEntryModel>>,
        val promotions: List<PromotionModel>,
        val currentCatalogEntryModel: CatalogEntryModel? = null,
        val currentCatalog: String = catalog.keys.firstOrNull() ?: "",
        val refreshing: Boolean = false,
        val cartValue: Int = 0,
        val setIdCatalogEntry: Set<Int> = setOf(),
    ): AnalyzesState(){
        fun positionInCatalogEntry(nameCategory: String) : Int{
            val index = catalog.keys.indexOf(nameCategory)
            return catalog.values.take(index)
                    .sumOf { list -> list.size }
        }

        fun categoryCatalog(position: Int): String{
            var offset = 0
            catalog.forEach { (key, value) ->
                if(position.inc() in offset until (value.size + offset)){
                    return key
                }else{
                    offset += value.size
                }
            }
            return catalog.keys.lastOrNull() ?: ""
        }
    }
}