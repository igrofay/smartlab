package com.example.smartlab.analyzes.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.smartlab.common.ui.button.BorderButton
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
fun CatalogEntryInformationView(
    catalogEntryModel: CatalogEntryModel?,
    added: Boolean,
    close: ()-> Unit,
    buy: ()-> Unit,
    remove: ()-> Unit,
) {
    when(catalogEntryModel){
        is CatalogEntryModel -> {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp,),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = catalogEntryModel.name,
                        fontWeight = FontWeight.W600,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    Box(
                        modifier = Modifier
                            .scaleClick(close)
                            .size(24.dp)
                            .background(MaterialTheme.colors.surface, CircleShape),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color(0xFF7E7E9A),
                            modifier = Modifier.size(10.dp)
                        )
                    }

                }
                Column{
                    Text(
                        text = "Описание",
                        fontWeight = FontWeight.W500,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.colorDescription,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = catalogEntryModel.description,
                        fontWeight = FontWeight.W400,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Подготовка",
                        fontWeight = FontWeight.W500,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.colorDescription,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = catalogEntryModel.preparation,
                        fontWeight = FontWeight.W400,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Text(
                            text = "Результаты через:",
                            fontWeight = FontWeight.W600,
                            fontFamily = sfProDisplayFontFamily,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.colorDescription,
                        )
                        Text(
                            text = catalogEntryModel.timeResult,
                            fontWeight = FontWeight.W500,
                            fontFamily = sfProDisplayFontFamily,
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                    }
                    Column (
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Text(
                            text = "Биоматериал:",
                            fontWeight = FontWeight.W600,
                            fontFamily = sfProDisplayFontFamily,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.colorDescription,
                        )
                        Text(
                            text = catalogEntryModel.bio,
                            fontWeight = FontWeight.W500,
                            fontFamily = sfProDisplayFontFamily,
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                    }
                }
                if (added){
                    BorderButton(label = "Убрать", onClick = remove)
                }else{
                    CustomButton(
                        label = "Взять за ${catalogEntryModel.price} ₽",
                        onClick = buy
                    )
                }
            }
        }
        null -> Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ){
            CircularProgressIndicator()
        }
    }

}