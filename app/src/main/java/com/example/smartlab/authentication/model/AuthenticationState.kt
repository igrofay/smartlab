package com.example.smartlab.authentication.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


internal data class AuthenticationState(
    val email: String = "",
    val isInputCodeFromEmail: Boolean = false,
    val code: String = "",
    val sizeCode: Int = 4,
    val flowTime: Flow<Int> = flowMinute() ,
    val isAuthorized: Boolean = false,
){
    companion object{
        fun flowMinute() = flow {
            for (second in (0..60).reversed()) {
                emit(second)
                delay(1000L)
            }
        }
    }
}