package com.example.data.data.repos

import android.util.Log
import android.util.Patterns
import com.example.data.data.di.RegularHttpClient
import com.example.data.data.model.MessageBody
import com.example.data.data.model.TokenBody
import com.example.data.domain.model.app.TokenModel
import com.example.data.domain.model.error.AppError
import com.example.data.domain.model.error.AuthenticationError
import com.example.data.domain.repos.AuthenticationRepos
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject

internal class AuthenticationReposImpl @Inject constructor(
    @RegularHttpClient private val httpClient: HttpClient,
): AuthenticationRepos {
    override suspend fun sendCode(email: String) : String{
        if (
            Patterns.EMAIL_ADDRESS
                .toRegex().matches(email)
        ){
            try {
                val answer = httpClient.post("/api/sendCode"){
                    header("email", email)
                }.body<MessageBody>()
                return answer.message
            }catch (error: ClientRequestException){
               throw AppError.SimpleMessage("Произошла ошибка на сервере")
            }
        }else{
            throw AuthenticationError.ErrorInEmail("Некорректная почта")
        }
    }

    override suspend fun signIn(email: String, code: String): TokenModel {
        try {
            val answer =  httpClient.post("/api/signin"){
                header("email", email)
                header("code", code)
            }
            Log.e("1", answer.bodyAsText())
            return answer.body<TokenBody>()
        }catch (error: ClientRequestException){
            if (error.response.status == HttpStatusCode.UnprocessableEntity){
                throw AuthenticationError.ErrorInCodeFromEmail("Неправильный код")
            }
            throw AppError.SimpleMessage("Произошла ошибка на сервере")
        }
    }
}