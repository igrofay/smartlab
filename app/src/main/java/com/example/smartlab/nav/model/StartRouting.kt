package com.example.smartlab.nav.model

internal sealed class StartRouting(val route: String){
    object Splash : StartRouting("splash")
    object Onboard : StartRouting("onboard")
    object Authentication : StartRouting("authentication")
    object CodeEntry: StartRouting("code_entry")
    companion object{
        const val route ="start"
    }
}