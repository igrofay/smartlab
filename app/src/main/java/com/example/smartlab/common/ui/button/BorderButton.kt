package com.example.smartlab.common.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@Composable
internal fun BorderButton(
    label: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .scaleClick(
                if (enabled) onClick else null
            )
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White, MaterialTheme.shapes.medium)
            .border(1.dp, lightGray, MaterialTheme.shapes.medium)
            .padding(vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontFamily = sfProDisplayFontFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black
        )
    }
}