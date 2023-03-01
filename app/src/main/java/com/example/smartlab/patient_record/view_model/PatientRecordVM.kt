package com.example.smartlab.patient_record.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import javax.inject.Inject

@HiltViewModel
internal class PatientRecordVM @Inject constructor(
    private val userRepos: UserRepos,
    savedStateHandle: SavedStateHandle,
): ViewModel(), EventBase<PatientRecordEvent> {
    private val isCreatingPatientRecord: Boolean = savedStateHandle
        .get<Boolean>(StartRouting.CreatingPatientRecord.arg1) == true
    private val _state = mutableStateOf(
        if (isCreatingPatientRecord) PatientRecordState.CreatingPatientRecord()
        else PatientRecordState.ChangingPatientRecord()
    )
    val state : State<PatientRecordState> by ::_state
    private val subFlowUserModel = viewModelScope.launch {
        userRepos.getUserModel().collect{ userModel ->
            userModel ?: return@collect
            _state.value = _state.value
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
        when(event){
            PatientRecordEvent.Save -> save()
            PatientRecordEvent.Skip -> {
                _state.value = (_state.value as? PatientRecordState.CreatingPatientRecord)?.copy(
                    followingActions = CreatingPatientRecordFollowingActions.GoToMainContent
                ) ?: return
            }
            is PatientRecordEvent.EnteringBirthday -> {

            }
            is PatientRecordEvent.EnteringFirstname -> {

            }
            is PatientRecordEvent.EnteringLastname -> {

            }
            is PatientRecordEvent.EnteringMiddleName -> {

            }
            is PatientRecordEvent.SelectedImage -> {

            }
            is PatientRecordEvent.SelectedGender -> {

            }

        }
    }
    private fun save(){
        job?.cancel()
        job = viewModelScope.launch {
            userRepos.updateUserModel(userModel = _state.value)
        }
    }


}