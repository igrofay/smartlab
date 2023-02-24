package com.example.smartlab.onboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.onboard.model.InformationApp

@Composable
fun InfoView(
   informationApp: InformationApp,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Text(
            text = informationApp.label,
            fontFamily = sfProDisplayFontFamily,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = informationApp.description,
            fontFamily = sfProDisplayFontFamily,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.colorDescription,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(0.6f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.8f))
        Image(
            painter = painterResource(informationApp.image),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth(0.98f)
        )
        Spacer(modifier = Modifier.weight(0.2f))
    }
}