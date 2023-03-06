package com.example.smartlab.nav.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

internal class BottomBarState {
    private val _isShow = mutableStateOf(true)
    val isShow: State<Boolean> by ::_isShow
    fun show(){
        _isShow.value = true
    }
    fun hide(){
        _isShow.value = false
    }
}