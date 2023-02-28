package com.example.smartlab.authentication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.authentication.model.AuthenticationEvent
import com.example.smartlab.authentication.model.AuthenticationState
import com.example.smartlab.common.ui.button.BackButton
import com.example.smartlab.common.ui.edit_text.DisplayCodeInput
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase

@Composable
internal fun InputCodeFromEmailView(
    state: AuthenticationState,
    eventBase: EventBase<AuthenticationEvent>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            BackButton {
                eventBase.onEvent(AuthenticationEvent.InputAnotherEmail)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Введите код из E-mail",
            fontFamily = sfProDisplayFontFamily,
            fontSize = 17.sp,
            color = Color.Black,
            fontWeight = FontWeight.W600,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))
        DisplayCodeInput(
            value = state.code,
            onValueChange = { eventBase.onEvent(AuthenticationEvent.InputCodeFromEmail(it)) },
            count = state.sizeCode
        )
        Spacer(modifier = Modifier.height(17.dp))
        val seconds by state.flowTime.collectAsState(initial = 60)
        Text(
            text = "Отправить код повторно можно будет через $seconds секунд",
            fontFamily = sfProDisplayFontFamily,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(0.7f),
            color = MaterialTheme.colors.colorDescription,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}