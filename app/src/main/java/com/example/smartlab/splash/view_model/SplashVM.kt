package com.example.smartlab.splash.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smartlab.splash.model.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(

): ViewModel() {
    private val _state = mutableStateOf<SplashState>(SplashState.Identification)
    val state: State<SplashState> by ::_state
    init {

    }
}