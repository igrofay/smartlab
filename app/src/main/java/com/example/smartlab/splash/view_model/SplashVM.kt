package com.example.smartlab.splash.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.domain.model.app.AppState
import com.example.data.domain.use_case.InitializationUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SplashVM @Inject constructor(
    initializationUserUseCase: InitializationUserUseCase,
): ViewModel() {
    private val _state = mutableStateOf<AppState?>(null)
    val state: State<AppState?> by ::_state
    init {
        initializationUserUseCase.execute()
            .onSuccess { appState: AppState ->
                _state.value = appState
            }
    }
}