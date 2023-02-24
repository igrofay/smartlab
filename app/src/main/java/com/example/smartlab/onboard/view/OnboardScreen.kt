package com.example.smartlab.onboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.onboard.model.InformationApp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen() {
    val list = listOf(
        InformationApp(
            "Анализы",
            "Экспресс сбор и получение проб",
            R.drawable.im_analyzes,
        ),
        InformationApp(
            "Уведомления",
            "Вы быстро узнаете о результатах",
            R.drawable.im_notifications
        ),
        InformationApp(
            "Мониторинг",
            "Наши врачи всегда наблюдают за вашими показателями здоровья",
            R.drawable.im_monitoring,
        )
    )
    val state = rememberPagerState()
    Column {
        Row(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxSize()
                .padding(start = 15.dp, top = 10.dp)
        ){
            Text(
                text = if (state.currentPage == 2)
                    "Завершить"
                else "Пропустить",
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color(0xFF57A9FF),
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
            IndicatorPositionView(
                count = list.size,
                current = state.currentPage,
                offsetY = maxHeight/ 3f
            )
            HorizontalPager(
                count = list.size,
                state = state,
                modifier = Modifier.fillMaxSize()
            ) {page ->
                InfoView(informationApp = list[page])
            }
        }

    }
}