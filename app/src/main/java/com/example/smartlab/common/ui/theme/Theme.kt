package com.example.smartlab.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = colorPrimary,
    onPrimary = colorOnPrimary,
    secondaryVariant = colorSecondaryVariant,
    background = Color.White,
    onBackground = Color.Black,
    surface = surfaceColor,
    error = red,
)

@Composable
internal fun SmartlabTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}