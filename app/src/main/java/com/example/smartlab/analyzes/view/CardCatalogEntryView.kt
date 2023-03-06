package com.example.smartlab.analyzes.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.smartlab.common.ui.button.BorderButton
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
fun CardCatalogEntryView(
    catalogEntryModel: CatalogEntryModel,
    added: Boolean,
    buttonClick: ()-> Unit,
    onClick:()->Unit,
) {
    Column(
        modifier = Modifier
            .scaleClick(onClick)
            .fillMaxWidth()
            .shadow(8.dp, MaterialTheme.shapes.medium)
            .background(Color.White, MaterialTheme.shapes.medium)
            .border(1.dp, lightGray, MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = catalogEntryModel.name,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            fontFamily = sfProDisplayFontFamily,
            color = Color.Black,
            maxLines = 2
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    text = catalogEntryModel.timeResult,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                    fontFamily = sfProDisplayFontFamily,
                    color = MaterialTheme.colors.colorDescription
                )
                Text(
                    text = "${catalogEntryModel.price} ₽",
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                    fontFamily = sfProDisplayFontFamily,
                    color = Color.Black
                )
            }
            if(added){
                BorderButton(
                    label = "Убрать",
                    onClick = buttonClick,
                    modifier = Modifier
                        .width(96.dp),
                    fontSize = 14.sp,
                    contentPaddingValues = PaddingValues(vertical = 10.dp)
                )
            }else{
                CustomButton(
                    label = "Добавить",
                    onClick = buttonClick,
                    modifier = Modifier
                        .width(96.dp),
                    fontSize = 14.sp,
                    contentPaddingValues = PaddingValues(vertical = 10.dp)
                )
            }
        }
    }
}