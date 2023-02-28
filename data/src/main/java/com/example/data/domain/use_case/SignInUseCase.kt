package com.example.data.domain.use_case

import com.example.data.domain.repos.AppRepos
import com.example.data.domain.repos.AuthenticationRepos
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticationRepos: AuthenticationRepos,
    private val appRepos: AppRepos,
) {
    suspend fun execute(email:String,code:String)= runCatching {
        val tokenModel = authenticationRepos.signIn(email, code)
        appRepos.setToken(tokenModel.token)
    }
}