package com.example.smartlab.patient_record.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.button.TextButton
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.patient_record.model.PatientRecordEvent
import com.example.smartlab.patient_record.model.PatientRecordState

@Composable
internal fun CreatingPatientRecordView(
    patientRecord: PatientRecordState.CreatingPatientRecord,
    eventBase: EventBase<PatientRecordEvent>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState())
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(
                text = "Создание карты пациента",
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W700,
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f)
            )
            TextButton("Пропустить") {
                eventBase.onEvent(PatientRecordEvent.Skip)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Без карты пациента вы не сможете заказать анализы.",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = sfProDisplayFontFamily,
            color = MaterialTheme.colors.colorDescription,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = sfProDisplayFontFamily,
            color = MaterialTheme.colors.colorDescription,
        )
        Spacer(modifier = Modifier.height(32.dp))
        PatientRecordFieldsView(patientRecord, eventBase)
    }
}