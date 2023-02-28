package com.example.smartlab.authentication.model

internal sealed class AuthenticationEvent {
    class InputEmail(val email:String): AuthenticationEvent()
    class InputCodeFromEmail(val code: String):AuthenticationEvent()
    object GetCodeFromEmail: AuthenticationEvent()
    object InputAnotherEmail: AuthenticationEvent()
}