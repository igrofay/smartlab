package com.example.smartlab.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smartlab.R

val sfProDisplayFontFamily = FontFamily(
    Font(R.font.sf_pro_display_thin, FontWeight.Thin), // 100
    Font(R.font.sf_pro_display_ultra_light, FontWeight.ExtraLight), // 200
    Font(R.font.sf_pro_display_light, FontWeight.Light), // 300
    Font(R.font.sf_pro_display_regular, FontWeight.Normal), // 400
    Font(R.font.sf_pro_display_medium, FontWeight.Medium), // 500
    Font(R.font.sf_pro_display_semi_bold, FontWeight.SemiBold), // 600
    Font(R.font.sf_pro_display_bold, FontWeight.Bold), // 700
    Font(R.font.sf_pro_display_heavy, FontWeight.ExtraBold), // 800
    Font(R.font.sf_pro_display_black, FontWeight.Black), // 900
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = sfProDisplayFontFamily,
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)