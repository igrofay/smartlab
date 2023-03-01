package com.example.smartlab.patient_record.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.patient_record.model.CreatingPatientRecordFollowingActions
import com.example.smartlab.patient_record.model.PatientRecordState
import com.example.smartlab.patient_record.view_model.PatientRecordVM


@Composable
internal fun PatientRecordScreen(
    goToMainContent: (()->Unit)? = null,
    viewModel: PatientRecordVM = hiltViewModel(),
) {
    when(val state = viewModel.state.value){
        is PatientRecordState.ChangingPatientRecord -> {

        }
        is PatientRecordState.CreatingPatientRecord -> {
            CreatingPatientRecordView(state, viewModel)
            LaunchedEffect(state.followingActions){
                when(state.followingActions){
                    CreatingPatientRecordFollowingActions.None -> {}
                    CreatingPatientRecordFollowingActions.GoToMainContent -> goToMainContent?.invoke()
                }
            }
        }
    }
}