package com.example.data.domain.use_case

import com.example.data.domain.model.app.AppState
import com.example.data.domain.repos.AppRepos
import javax.inject.Inject

class InitializationUserUseCase @Inject constructor(
    private val appRepos: AppRepos,
){
    fun execute() = runCatching {
        if (appRepos.token == null){
            if (appRepos.isUserFamiliarWithApp){
                AppState.AuthenticationEmail
            }else{
                AppState.FirsTime
            }
        }else{
            if (appRepos.isCodeSetForEnterApp){
                AppState.AuthenticationCode
            }else{
                AppState.Authorization
            }
        }
    }
}