package com.example.smartlab.onboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.onboard.model.InformationApp
import com.example.smartlab.onboard.model.OnboardState

@Composable
internal fun InformationAppView(
    state: OnboardState.DisplayInformationApp,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Text(
            text = state.informationApp.label,
            fontFamily = sfProDisplayFontFamily,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 20.sp,
            modifier = Modifier
                .testTag("label"),
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = state.informationApp.description,
            fontFamily = sfProDisplayFontFamily,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.colorDescription,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .heightIn(min = 40.dp)
                .testTag("description"),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.8f))
        IndicatorPositionView(
            count = state.count,
            current = state.current,
        )
        Spacer(modifier = Modifier.weight(0.8f))
        Image(
            painter = painterResource(state.informationApp.image),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth(0.98f)
                .testTag(state.informationApp.image.toString()),
        )
        Spacer(modifier = Modifier.weight(0.2f))
    }
}