package com.example.smartlab.code_entry.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun NumberOfFilledCharactersView(
    count: Int,
    size: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in 1..size){
            val animationAlpha by animateFloatAsState(
                targetValue = if (i <= count) 1f else 0f
            )
            Spacer(
                modifier = Modifier
                    .size(16.dp)
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                    .background(
                        MaterialTheme.colors.primary.copy(animationAlpha),
                        CircleShape
                    )
            )
        }
    }
}