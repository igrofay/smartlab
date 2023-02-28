package com.example.data.domain.repos

import com.example.data.domain.model.app.TokenModel

interface AuthenticationRepos {
    suspend fun sendCode(email:String): String
    suspend fun signIn(email: String, code:String): TokenModel
}