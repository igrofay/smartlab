package com.example.smartlab.splash.model

sealed class SplashState {
    object Identification: SplashState()
    object NeedAuthentication : SplashState()
    object FirstTime: SplashState()
    object Authorization: SplashState()
}