package com.example.data.data.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.data.domain.repos.AppRepos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    private const val keySharedPreferences = "keySharedPreferences"
    private const val urlApi = "https://medic.madskill.ru"
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(keySharedPreferences, Context.MODE_PRIVATE)
    @Provides
    @Singleton
    @RegularHttpClient
    fun provideRegularHttpClient() = HttpClient(Android){
        defaultRequest {
            url(urlApi)
        }
        expectSuccess = true
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }.apply {
        plugin(HttpSend).intercept { request->
            Log.e("HttpClient::", request.url.toString())
            execute(request)
        }
    }
    @Provides
    @Singleton
    @AuthorizedHttpClient
    fun provideAuthorizedHttpClient(appRepos: AppRepos) : HttpClient {
        val client = HttpClient(Android){
            defaultRequest {
                url(urlApi)
            }
            expectSuccess = true
            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 5)
                exponentialDelay()
            }
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        client.plugin(HttpSend).intercept {request ->
            request.header("Authorization", "Bearer ${appRepos.token}")
            execute(request)
        }
        return client
    }
}