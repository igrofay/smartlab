package com.example.smartlab.common.view_model

internal interface EventBase<T> {
    fun onEvent(event: T)
}