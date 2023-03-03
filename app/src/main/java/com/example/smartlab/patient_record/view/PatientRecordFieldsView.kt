package com.example.smartlab.patient_record.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.dropdown_menu.CustomDropdownMenu
import com.example.smartlab.common.ui.edit_text.CustomTextField
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.patient_record.model.PatientRecordEvent
import com.example.smartlab.patient_record.model.PatientRecordState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
internal fun PatientRecordFieldsView(
    patientRecord: PatientRecordState,
    eventBase: EventBase<PatientRecordEvent>,
) {
    val dialogState = rememberMaterialDialogState()
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        CustomTextField(
            text = patientRecord.firstname,
            onTextChange = {
                eventBase.onEvent(PatientRecordEvent.EnteringFirstname(it))
            },
            hint = "Имя"
        )
        CustomTextField(
            text = patientRecord.middleName,
            onTextChange = {
                eventBase.onEvent(PatientRecordEvent.EnteringMiddleName(it))
            },
            hint = "Отчество"
        )
        CustomTextField(
            text = patientRecord.lastname,
            onTextChange = {
                eventBase.onEvent(PatientRecordEvent.EnteringLastname(it))
            },
            hint = "Фамилия"
        )
        CustomTextField(
            text = patientRecord.birthday,
            onTextChange = {},
            hint = "Дата рождения",
            readOnly = true,
            modifier = Modifier.scaleClick { dialogState.show() }
        )
        CustomDropdownMenu(
            value = patientRecord.gender,
            onValueChange = {
                eventBase.onEvent(PatientRecordEvent.SelectedGender(it))
            },
            values = listOf("Мужской", "Женский")
        )
        CustomButton(
            label = when(patientRecord){
                is PatientRecordState.ChangingPatientRecord -> "Сохранить"
                is PatientRecordState.CreatingPatientRecord -> "Создать"
            },
            onClick = { eventBase.onEvent(PatientRecordEvent.Save) },
            enabled = patientRecord.isPossibleToSave(),
        )

    }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        },
    ) {
        datepicker(
            yearRange = IntRange(1900, LocalDate.now().year),
            title = "Выберите дату рождения"
        ){ date ->
            eventBase.onEvent(PatientRecordEvent.EnteringBirthday(date))
        }
    }
}