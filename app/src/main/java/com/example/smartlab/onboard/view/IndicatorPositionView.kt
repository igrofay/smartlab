package com.example.smartlab.onboard.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun IndicatorPositionView(
    count: Int,
    current: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        for (item in 0 until count){
            val animationAlpha by animateFloatAsState(
                targetValue = if (item == current) 1f else 0f
            )
            Spacer(
                modifier = Modifier
                    .size(15.dp)
                    .border(1.dp, Color(0xFF57A9FF), CircleShape)
                    .background(Color(0xFF57A9FF).copy(animationAlpha), CircleShape)
            )
        }
    }
}