package com.example.smartlab.patient_record.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.repos.UserRepos
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.nav.model.StartRouting
import com.example.smartlab.patient_record.model.CreatingPatientRecordFollowingActions
import com.example.smartlab.patient_record.model.PatientRecordEvent
import com.example.smartlab.patient_record.model.PatientRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
internal class PatientRecordVM @Inject constructor(
    private val userRepos: UserRepos,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), EventBase<PatientRecordEvent> {
    private val isCreatingPatientRecord: Boolean = savedStateHandle
        .get<Boolean>(StartRouting.CreatingPatientRecord.arg1) == null
    private val _state = mutableStateOf(
        if (isCreatingPatientRecord) PatientRecordState.CreatingPatientRecord()
        else PatientRecordState.ChangingPatientRecord()
    )
    val state: State<PatientRecordState> by ::_state
    private val subFlowUserModel = viewModelScope.launch {
        userRepos.getUserModel().collect { userModel ->
            userModel ?: return@collect
            _state.value = PatientRecordState
                .toPatientRecordState(isCreatingPatientRecord, userModel)
        }
    }
    private var job: Job? = null


    override fun onCleared() {
        super.onCleared()
        subFlowUserModel.cancel()
        job?.cancel()
    }

    override fun onEvent(event: PatientRecordEvent) {
        when (event) {
            PatientRecordEvent.Save -> save()
            PatientRecordEvent.Skip -> {
                _state.value = (_state.value as? PatientRecordState.CreatingPatientRecord)?.copy(
                    followingActions = CreatingPatientRecordFollowingActions.GoToMainContent
                ) ?: return
            }
            is PatientRecordEvent.EnteringBirthday -> {
                val birthday =
                    "${event.birthday.dayOfMonth} ${event.birthday.month.toString().lowercase()} ${event.birthday.year}"
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            birthday = birthday,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            birthday = birthday
                        )
                    }
                }
            }
            is PatientRecordEvent.EnteringFirstname -> {
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            firstname = event.firstname,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            firstname = event.firstname
                        )
                    }
                }
            }
            is PatientRecordEvent.EnteringLastname -> {
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            lastname = event.lastname,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            lastname = event.lastname
                        )
                    }
                }
            }
            is PatientRecordEvent.EnteringMiddleName -> {
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            middleName = event.middleName,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            middleName = event.middleName
                        )
                    }
                }
            }
            is PatientRecordEvent.SelectedImage -> {
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            image = event.pathImage,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            image = event.pathImage
                        )
                    }
                }
            }
            is PatientRecordEvent.SelectedGender -> {
                _state.value = when (val localState = _state.value) {
                    is PatientRecordState.ChangingPatientRecord -> {
                        localState.copy(
                            gender = event.gender,
                            isEdit = true,
                        )
                    }
                    is PatientRecordState.CreatingPatientRecord -> {
                        localState.copy(
                            gender = event.gender
                        )
                    }
                }
            }

        }
    }

    private fun save() {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                userRepos.updateUserModel(userModel = _state.value)
            }catch (e:Throwable){
                onError(e)
            }
        }
    }

    private fun onError(e:Throwable){
        Log.e("PatientRecordVM::", e.message.toString())
    }

}