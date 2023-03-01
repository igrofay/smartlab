package com.example.smartlab.common.ui.button

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
internal fun TextButton(
    label: String,
    onClick: ()->Unit
) {
    Text(
        text = label,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        fontFamily = sfProDisplayFontFamily,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.alphaClick(onClick),
    )
}