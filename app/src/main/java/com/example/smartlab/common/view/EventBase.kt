package com.example.smartlab.common.view

interface EventBase<T> {
    fun onEvent(event: T)
}