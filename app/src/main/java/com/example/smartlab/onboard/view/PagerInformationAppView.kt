package com.example.smartlab.onboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.onboard.model.OnboardEvent
import com.example.smartlab.onboard.model.OnboardState

@Composable
internal fun PagerInformationAppView(
    state: OnboardState.DisplayInformationApp,
    eventBase: EventBase<OnboardEvent>
) {
    Column {
        Row(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxSize()
                .padding(start = 15.dp, top = 10.dp)
        ){
            Text(
                text = if (state.isEnd)
                    "Завершить"
                else "Пропустить",
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color(0xFF57A9FF),
                modifier = Modifier.alphaClick {
                    eventBase.onEvent(OnboardEvent.NextSlide)
                }.testTag("next")
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ic_logo_shape),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(),
                alignment = Alignment.TopEnd
            )
        }
        BoxWithConstraints(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ){
            InformationAppView(state)
        }
    }
}