package com.example.smartlab.onboard.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.onboard.model.OnboardState
import com.example.smartlab.onboard.view_model.OnboardVM

@Composable
internal fun OnboardScreen(
    goToAuthentication: ()->Unit,
    viewModel: OnboardVM = hiltViewModel()
) {
    when(val state = viewModel.state.value){
        is OnboardState.DisplayInformationApp -> {
            PagerInformationAppView(state, viewModel)
        }
        OnboardState.FinishWatching -> LaunchedEffect(Unit){
            goToAuthentication()
        }
    }

}