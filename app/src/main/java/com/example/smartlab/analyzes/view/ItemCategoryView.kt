package com.example.smartlab.analyzes.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
internal fun CardPromoView(
    label:String,
    selected: Boolean,
    onClick: ()-> Unit,
) {
    val animatorColorBox by animateColorAsState(
        targetValue = if(selected) MaterialTheme.colors.primary
        else MaterialTheme.colors.surface,
    )
    val animatorColorText by animateColorAsState(
        targetValue = if(selected) Color.White
        else MaterialTheme.colors.colorDescription,
    )
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .alphaClick(onClick)
            .background(animatorColorBox)
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ){
        Text(
            text = label,
            fontFamily = sfProDisplayFontFamily,
            fontSize = 15.sp,
            color = animatorColorText,
            fontWeight = FontWeight.W500,
        )
    }
}