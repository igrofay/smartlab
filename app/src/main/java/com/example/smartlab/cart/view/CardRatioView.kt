package com.example.smartlab.cart.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.button.AddOrReduceButton
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
internal fun CardRatioView(
    name: String,
    number: Int,
    price: String,
    add: ()-> Unit,
    decrease: ()-> Unit,
    delete: ()->Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background, MaterialTheme.shapes.medium)
            .border(1.dp, Color(0xFFF4F4F4),MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row{
            Text(
                text = name,
                fontWeight = FontWeight.W500,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(20.dp)
                    .scaleClick(delete),
                tint = MaterialTheme.colors.colorDescription
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = "$price ₽",
                fontWeight = FontWeight.W500,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 17.sp,
                color = Color.Black,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    text = "$number пациент",
                    fontFamily = sfProDisplayFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
                AddOrReduceButton(
                    add = add,
                    reduce = decrease,
                    enabledAdd = number <2,
                    enabledReduce = number>1,
                )
            }
        }
    }
}