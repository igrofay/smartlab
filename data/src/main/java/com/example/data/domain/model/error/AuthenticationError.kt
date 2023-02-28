package com.example.data.domain.model.error

sealed class AuthenticationError : AppError() {
    class ErrorInEmail(override val message: String) : AuthenticationError()
    class ErrorInCodeFromEmail(override val message: String) : AuthenticationError()
}