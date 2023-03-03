package com.example.smartlab.patient_record.model

import java.time.LocalDate

internal sealed class PatientRecordEvent {
    object Save: PatientRecordEvent()
    object Skip: PatientRecordEvent()
    class EnteringFirstname(val firstname: String): PatientRecordEvent()
    class EnteringLastname(val lastname:String): PatientRecordEvent()
    class EnteringMiddleName(val middleName: String): PatientRecordEvent()
    class EnteringBirthday(val birthday: LocalDate): PatientRecordEvent()
    class SelectedImage(val pathImage:String): PatientRecordEvent()
    class SelectedGender(val gender:String): PatientRecordEvent()
}