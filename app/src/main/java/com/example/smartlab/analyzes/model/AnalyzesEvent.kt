package com.example.smartlab.analyzes.model

import com.example.data.domain.model.catalog.CatalogEntryModel

internal sealed class AnalyzesEvent {
    class OpenCatalogEntry(val catalogEntry: CatalogEntryModel) : AnalyzesEvent()
    object CloseCatalogEntry : AnalyzesEvent()
    class SelectedCategoryCatalog(val category: String,) : AnalyzesEvent()
    object Refresh :  AnalyzesEvent()
    class Add(val catalogEntry: CatalogEntryModel): AnalyzesEvent()
    class Remove(val catalogEntry: CatalogEntryModel): AnalyzesEvent()

}