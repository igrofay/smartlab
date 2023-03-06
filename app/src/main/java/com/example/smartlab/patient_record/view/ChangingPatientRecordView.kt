package com.example.smartlab.patient_record.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.patient_record.model.PatientRecordEvent
import com.example.smartlab.patient_record.model.PatientRecordState
import com.skydoves.landscapist.glide.GlideImage
import com.example.smartlab.R
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.colorDescription

@Composable
internal fun ChangingPatientRecordView(
    state: PatientRecordState.ChangingPatientRecord,
    eventBase: EventBase<PatientRecordEvent>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Карта пациента",
            fontFamily = sfProDisplayFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
        )
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult ={ uri: Uri? -> uri?.let { uriIsNotNull->
                eventBase.onEvent(PatientRecordEvent.SelectedImage(uriIsNotNull.toString()))
            } }
        )
        GlideImage(
            imageModel = { state.image },
            modifier = Modifier
                .width(148.dp)
                .height(123.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color(0xFFD9D9D9))
                .alphaClick {
                    launcher.launch("image/*")
                },
            failure = {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(54.dp)
                )
            },
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        )
        Column {
            Text(
                text = "Без карты пациента вы не сможете заказать анализы.",
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = sfProDisplayFontFamily,
                color = MaterialTheme.colors.colorDescription,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = sfProDisplayFontFamily,
                color = MaterialTheme.colors.colorDescription,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        PatientRecordFieldsView(patientRecord = state, eventBase = eventBase)
    }
}