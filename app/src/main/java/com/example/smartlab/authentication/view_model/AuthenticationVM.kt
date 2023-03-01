package com.example.smartlab.authentication.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.model.error.AppError
import com.example.data.domain.repos.AuthenticationRepos
import com.example.data.domain.use_case.SignInUseCase
import com.example.smartlab.authentication.model.AuthenticationEvent
import com.example.smartlab.authentication.model.AuthenticationState
import com.example.smartlab.common.view_model.EventBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthenticationVM @Inject constructor(
    private val authenticationRepos: AuthenticationRepos,
    private val signInUseCase: SignInUseCase,
) : ViewModel(), EventBase<AuthenticationEvent> {
    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> by ::_state

    private val _errorMessage = mutableStateOf<AppError?>(null)
    val errorMessage: State<AppError?> by::_errorMessage

    private var job : Job? = null

    override fun onEvent(event: AuthenticationEvent) {
        when(event){
            AuthenticationEvent.GetCodeFromEmail -> sendCode()
            AuthenticationEvent.InputAnotherEmail -> {
                job?.cancel()
                _state.value = AuthenticationState()
            }
            is AuthenticationEvent.EnteringCodeFromEmail -> {
                val localState = _state.value
                if (
                    event.code.isEmpty() ||
                    (event.code.toIntOrNull() != null && event.code.length <= localState.sizeCode)
                ){
                    _state.value = localState.copy(code = event.code)
                }
                if (_state.value.code.length == localState.sizeCode){
                    signIn()
                }
            }
            is AuthenticationEvent.EnteringEmail -> {
                _state.value = _state.value.copy(email = event.email)
            }
        }
    }

    private fun signIn(){
        job?.cancel()
        job = viewModelScope.launch {
            val email = _state.value.email
            val code =  _state.value.code
            signInUseCase
                .execute(email, code)
                .onSuccess {
                    _state.value = _state.value.copy(isAuthorized = true)
                }
                .onFailure(::onError)
        }
    }

    private fun onError(error: Throwable){
        if (error is AppError){
            _errorMessage.value = error
        }else{
            Log.e("AuthenticationVM::", error.toString())
        }
    }

    private fun sendCode(){
        job?.cancel()
        job = viewModelScope.launch {
            val email = _state.value.email
            try {
                val message = authenticationRepos.sendCode(email)
                _errorMessage.value = AppError.SimpleMessage(message)
                _state.value = _state.value.copy(
                    isInputCodeFromEmail = true,
                    flowTime = AuthenticationState.flowMinute()
                )
            }catch (e: Throwable){
                onError(e)
            }
        }
    }


}