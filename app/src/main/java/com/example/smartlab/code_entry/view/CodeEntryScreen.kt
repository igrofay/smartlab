package com.example.smartlab.code_entry.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.code_entry.model.CodeEntryEvent
import com.example.smartlab.code_entry.model.CodeEntryFollowingActions
import com.example.smartlab.code_entry.view_model.CodeEntryVM
import com.example.smartlab.common.ui.button.TextButton
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun CodeEntryScreen(
    goToCreatePatientChart: ()->Unit,
    goToOpenMainContent: () -> Unit,
    viewModel: CodeEntryVM = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val state by viewModel.state
    LaunchedEffect(viewModel.errorMessage.value){
        val error = viewModel.errorMessage.value
        error?.message?.let { message->
            scaffoldState
                .snackbarHostState
                .showSnackbar(message)
        }
    }
    LaunchedEffect(state.codeEntryFollowingActions){
        when(state.codeEntryFollowingActions){
            CodeEntryFollowingActions.None -> {}
            CodeEntryFollowingActions.CreatePatientChart -> goToCreatePatientChart()
            CodeEntryFollowingActions.OpenMainContent -> goToOpenMainContent()
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
                if(state.isCreatingNewCode){
                    TextButton(label = "Пропустить") {
                        viewModel.onEvent(CodeEntryEvent.SkipCreatingNewCode)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (state.isCreatingNewCode)
                    "Создайте пароль"
                else "Введите пароль",
                fontFamily = sfProDisplayFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Для защиты ваших персональных данных",
                fontWeight = FontWeight.W400,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 15.sp,
                color = MaterialTheme.colors.colorDescription
            )
            Spacer(modifier = Modifier.weight(1f))
            NumberOfFilledCharactersView(count = state.code.length, size = state.sizeCode)
            Spacer(modifier = Modifier.weight(1f))
            KeyboardView(viewModel)
            Spacer(modifier = Modifier.weight(1.5f))
        }
    }
}