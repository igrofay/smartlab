package com.example.data.domain.repos

interface AppRepos {
    // For Onboard
    val isUserFamiliarWithApp : Boolean
    fun userHasBeenIntroducedWithApp()
    // For Api Request
    val token: String?
    fun setToken(token: String)
    // For enter app
    val isCodeSetForEnterApp: Boolean
    fun checkEnteredCode(code: String): Boolean
    fun setNewCode(code: String)

}