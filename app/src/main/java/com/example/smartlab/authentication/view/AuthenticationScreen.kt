package com.example.smartlab.authentication.view

import android.annotation.SuppressLint
import android.net.MacAddress
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.authentication.view_model.AuthenticationVM

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun AuthenticationScreen(
    goToCodeEntry: ()->Unit,
    viewModel: AuthenticationVM = hiltViewModel()
) {
    val state by viewModel.state
    LaunchedEffect(state.isAuthorized){
        if(state.isAuthorized){
            goToCodeEntry()
        }
    }
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(viewModel.errorMessage.value){
        val error = viewModel.errorMessage.value
        error?.message?.let { message->
            scaffoldState
                .snackbarHostState
                .showSnackbar(message)
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd
        ){
            InputEmailView(state, viewModel)
            AnimatedVisibility(
                visible = state.isInputCodeFromEmail,
                enter = expandHorizontally(
                    expandFrom = Alignment.Start
                ),
                exit = shrinkHorizontally(
                    shrinkTowards = Alignment.Start,
                ),
            ) {
                InputCodeFromEmailView(state,viewModel)
            }
        }
    }
}