package com.example.smartlab.authentication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.authentication.model.AuthenticationEvent
import com.example.smartlab.authentication.model.AuthenticationState
import com.example.smartlab.common.ui.button.BorderButton
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.edit_text.CustomTextField
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase

@Composable
internal fun InputEmailView(
    state: AuthenticationState,
    eventBase: EventBase<AuthenticationEvent>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Image(
                painter = painterResource(R.drawable.im_hello),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "Добро пожаловать!",
                fontFamily = sfProDisplayFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Войдите, чтобы пользоваться функциями приложения",
            fontFamily = sfProDisplayFontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.weight(0.25f))
        CustomTextField(
            text = state.email,
            onTextChange = {eventBase.onEvent(AuthenticationEvent.EnteringEmail(it))},
            labelTop = "Вход по E-mail",
            hint = "E-mail"
        )
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(
            label = "Далее",
            onClick = {eventBase.onEvent(AuthenticationEvent.GetCodeFromEmail)},
            enabled = state.email.isNotBlank()
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Или войдите с помощью",
            fontFamily = sfProDisplayFontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.colorDescription,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        BorderButton(
            label = "Войти с Яндекс",
            onClick = { /*TODO*/ },
        )
        Spacer(modifier = Modifier.weight(0.2f))
    }
}