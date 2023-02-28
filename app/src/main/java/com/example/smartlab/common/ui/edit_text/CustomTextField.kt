package com.example.smartlab.common.ui.edit_text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily


@Composable
internal fun CustomTextField(
    text:String,
    onTextChange: (String)->Unit,
    modifier: Modifier = Modifier,
    labelTop:String? = null,
    hint:String?=null,
    singleLine: Boolean = true
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        labelTop?.let { label->
            Text(
                text = label,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.colorDescription
            )
        }
        BasicTextField(
            value = text ,
            onValueChange = onTextChange,
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W400,
                color = Color.Black.copy(0.5f)
            ),
            singleLine = singleLine,
        ){innerTextField->
            Box(
                modifier = modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
                    .border(1.dp, lightGray, MaterialTheme.shapes.medium)
                    .padding(14.dp)
            ){
                if (text.isEmpty() && hint != null){
                    Text(
                        text = hint,
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colors.colorDescription
                    )
                }
                innerTextField()
            }
        }
    }
}