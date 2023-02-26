package com.example.data.domain.repos

interface AppRepos {
    val isUserFamiliarWithApp : Boolean
    fun userHasBeenIntroducedWithApp()
}