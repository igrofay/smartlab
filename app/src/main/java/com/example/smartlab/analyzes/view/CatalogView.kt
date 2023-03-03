package com.example.smartlab.analyzes.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.data.domain.model.catalog.CatalogEntryModel

@Composable
internal fun CatalogView(
    catalog: Map<String, List<CatalogEntryModel>>,
    onDisplayCategory: (String)-> Unit,
    lazyListState: LazyListState,
) {
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        for (catalogItem in catalog){
            items(catalogItem.value){ catalogEntryModel->
                onDisplayCategory(catalogItem.key)
                CardCatalogEntryView(catalogEntryModel)
            }
        }

    }
}