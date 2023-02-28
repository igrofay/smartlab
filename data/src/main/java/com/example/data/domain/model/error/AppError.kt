package com.example.data.domain.model.error

sealed class AppError: Exception(){
    class SimpleMessage(override val message: String):AppError()
}