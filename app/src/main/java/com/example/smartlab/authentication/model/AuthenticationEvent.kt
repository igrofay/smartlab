package com.example.smartlab.authentication.model

internal sealed class AuthenticationEvent {
    class EnteringEmail(val email:String): AuthenticationEvent()
    class EnteringCodeFromEmail(val code: String):AuthenticationEvent()
    object GetCodeFromEmail: AuthenticationEvent()
    object InputAnotherEmail: AuthenticationEvent()
}