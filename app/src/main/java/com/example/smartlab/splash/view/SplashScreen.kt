package com.example.smartlab.splash.view

import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.splash.model.SplashState
import com.example.smartlab.splash.view_model.SplashVM

@Composable
fun SplashScreen(
    goToOnboard : ()-> Unit,
    goToAuthentication: ()->Unit,
    goToMainContent: ()-> Unit,
    viewModel : SplashVM = hiltViewModel(),
) {
    val activity = LocalContext.current as? ComponentActivity
    SideEffect {
        activity?.window?.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }
    when(viewModel.state.value){
        SplashState.Authorization -> LaunchedEffect(Unit){
            goToMainContent()
        }
        SplashState.FirstTime -> LaunchedEffect(Unit){
            goToOnboard()
        }
        SplashState.Identification -> SplashLogoView()
        SplashState.NeedAuthentication -> LaunchedEffect(Unit){
            goToAuthentication()
        }
    }


}