package com.example.smartlab.code_entry.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.data.domain.model.error.AppError
import com.example.data.domain.repos.AppRepos
import com.example.smartlab.code_entry.model.CodeEntryEvent
import com.example.smartlab.code_entry.model.CodeEntryState
import com.example.smartlab.code_entry.model.CodeEntryFollowingActions
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.nav.model.StartRouting
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CodeEntryVM @Inject constructor(
    private val appRepos: AppRepos,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), EventBase<CodeEntryEvent>{
    private val needCreateNewCode: Boolean = savedStateHandle
        .get<Boolean>(StartRouting.CodeEntry.arg1) == true
    private val _state = mutableStateOf(
        CodeEntryState(isCreatingNewCode = needCreateNewCode)
    )
    val state: State<CodeEntryState> by::_state
    private val _errorMessage = mutableStateOf<AppError?>(null)
    val errorMessage: State<AppError?> by::_errorMessage

    override fun onEvent(event: CodeEntryEvent) {
        when(event){
            CodeEntryEvent.DeleteCodeCharacter -> {
                val localState = _state.value
                _state.value = localState.copy(
                    code = localState.code.dropLast(1)
                )
            }
            is CodeEntryEvent.EnteringCodeCharacter ->{
                val localState = _state.value
                val code = localState.code + event.char
                if(code.length <= localState.sizeCode){
                    _state.value = localState.copy(code = code)
                }
                if (code.length == localState.sizeCode){
                    if (localState.isCreatingNewCode){
                        saveNewCode()
                    }else{
                        checkEntryCode()
                    }
                }
            }
            CodeEntryEvent.SkipCreatingNewCode -> {
                val localState = _state.value
                _state.value = localState.copy(
                    codeEntryFollowingActions = CodeEntryFollowingActions.CreatePatientChart
                )
            }
        }
    }

    private fun checkEntryCode(){
        val localState = _state.value
        val isEnteredCodeCorrect = appRepos.checkEnteredCode(localState.code)
        if(isEnteredCodeCorrect){
            _state.value = localState.copy(
                codeEntryFollowingActions = CodeEntryFollowingActions.OpenMainContent
            )
        }else{
            _errorMessage.value = AppError.SimpleMessage("Неверный пароль")
        }
    }
    private fun saveNewCode(){
        val localState = _state.value
        appRepos.setNewCode(localState.code)
        _state.value = localState.copy(
            codeEntryFollowingActions = CodeEntryFollowingActions.CreatePatientChart
        )
    }
}