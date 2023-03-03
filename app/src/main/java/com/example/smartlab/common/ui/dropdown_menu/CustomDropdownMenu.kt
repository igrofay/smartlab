package com.example.smartlab.common.ui.dropdown_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
fun <T>CustomDropdownMenu(
    value: T,
    onValueChange: (T)-> Unit,
    values: List<T>,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Box{
        Row(
            modifier = modifier
                .scaleClick {
                    expanded = true
                }
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
                .border(1.dp, lightGray, MaterialTheme.shapes.medium)
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value.toString(),
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = Color.Black,
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.colorDescription
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .background(MaterialTheme.colors.surface)
        ) {
            values.forEach { item ->
                DropdownMenuItem(onClick = {
                   onValueChange(item)
                    expanded = false
                }) {
                    Text(
                        text = item.toString(),
                        fontFamily = sfProDisplayFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }

}