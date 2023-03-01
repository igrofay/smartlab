package com.example.smartlab.splash.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.domain.model.app.AppState
import com.example.smartlab.splash.view_model.SplashVM

@Composable
internal fun SplashScreen(
    goToOnboard : ()-> Unit,
    goToAuthentication: ()->Unit,
    goToMainContent: ()-> Unit,
    goToCodeEntry: ()->Unit,
    viewModel : SplashVM = hiltViewModel(),
) {
//    val activity = LocalContext.current as? ComponentActivity
//    SideEffect {
//        activity?.window?.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
//    }
    LaunchedEffect(viewModel.state.value){
        when(viewModel.state.value){
            AppState.FirsTime -> goToOnboard()
            AppState.AuthenticationEmail -> goToAuthentication()
            AppState.AuthenticationCode -> goToCodeEntry()
            AppState.Authorization -> goToMainContent()
            null -> {}
        }

    }
    SplashLogoView()
}