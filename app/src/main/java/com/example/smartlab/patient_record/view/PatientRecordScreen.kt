package com.example.smartlab.patient_record.view

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.patient_record.model.CreatingPatientRecordFollowingActions
import com.example.smartlab.patient_record.model.PatientRecordEvent
import com.example.smartlab.patient_record.model.PatientRecordState
import com.example.smartlab.patient_record.view_model.PatientRecordVM


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun PatientRecordScreen(
    goToMainContent: (()->Unit)? = null,
    viewModel: PatientRecordVM = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(viewModel.errorMessage.value){
        viewModel.errorMessage.value?.message?.let {message->
            scaffoldState.snackbarHostState.showSnackbar(message)
            viewModel.onEvent(PatientRecordEvent.Send)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ){
        when(val state = viewModel.state.value){
            is PatientRecordState.ChangingPatientRecord -> {
                ChangingPatientRecordView(state, viewModel)
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
}