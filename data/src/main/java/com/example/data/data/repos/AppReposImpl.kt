package com.example.data.data.repos

import com.example.data.domain.repos.AppRepos
import javax.inject.Inject


internal class AppReposImpl @Inject constructor(): AppRepos {
    override val isUserFamiliarWithApp: Boolean
        get() = false

    override fun userHasBeenIntroducedWithApp() {

    }
}