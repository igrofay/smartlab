package com.example.smartlab.analyzes.view

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.analyzes.model.AnalyzesState
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.edit_text.CustomTextField
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
internal fun ContentView(
    state: AnalyzesState.Success,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        CustomTextField(
            text = "",
            onTextChange = {},
            hint = "Искать анализы",
            icon = R.drawable.ic_search,
            readOnly = true,
            modifier = Modifier.scaleClick {

            }
        )
        val lazyListState = rememberLazyListState()
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(
                text = "Акции и новости",
                fontWeight = FontWeight.W600,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 17.sp,
                color = MaterialTheme.colors.colorDescription
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(state.promotions){promotionModel->
                    ItemPromotion(promotionModel)
                }
            }
        }
        var currentCatalog by remember {
            mutableStateOf("")
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(
                text = "Каталог анализов",
                fontWeight = FontWeight.W600,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 17.sp,
                color = MaterialTheme.colors.colorDescription
            )
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (item in state.catalog.keys){
                    CardPromoView(item, currentCatalog == item){

                    }
                }
            }
        }
        CatalogView(
            catalog = state.catalog,
            onDisplayCategory = {
                currentCatalog = it
            },
            lazyListState = lazyListState,
        )
    }
}