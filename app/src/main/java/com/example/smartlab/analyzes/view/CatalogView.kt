package com.example.smartlab.analyzes.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.smartlab.analyzes.model.AnalyzesEvent
import com.example.smartlab.common.view_model.EventBase


@Composable
internal fun CatalogView(
    catalog: Map<String, List<CatalogEntryModel>>,
    setId: Set<Int>,
    lazyListState: LazyListState,
    eventBase: EventBase<AnalyzesEvent>,
) {
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 20.dp)
    ){
        for (catalogItem in catalog){
            items(
                catalogItem.value, contentType = { it.category }
            ){ catalogEntryModel->
                CardCatalogEntryView(
                    catalogEntryModel,
                    added = setId.contains(catalogEntryModel.id),
                    buttonClick = {
                        if (setId.contains(catalogEntryModel.id))
                            eventBase.onEvent(AnalyzesEvent.Remove(catalogEntryModel))
                        else
                            eventBase.onEvent(AnalyzesEvent.Add(catalogEntryModel))
                    }
                ){
                    eventBase.onEvent(
                        AnalyzesEvent.OpenCatalogEntry(catalogEntryModel)
                    )
                }
            }

        }
    }
}