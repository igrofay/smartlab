package com.example.smartlab.patient_record.model

import com.example.data.domain.model.user.UserModel

internal sealed interface PatientRecordState : UserModel {
    data class CreatingPatientRecord(
        override val firstname: String = "",
        override val lastname: String = "",
        override val middleName: String = "",
        override val birthday: String = "",
        override val image: String? = null,
        override val gender: String = "Мужской",
        val followingActions: CreatingPatientRecordFollowingActions = CreatingPatientRecordFollowingActions.None,
    ): PatientRecordState
    data class ChangingPatientRecord(
        override val firstname: String = "",
        override val lastname: String = "",
        override val middleName: String = "",
        override val birthday: String = "",
        override val image: String? = null,
        override val gender: String = "Мужской",
        val isEdit: Boolean = false,
    ) : PatientRecordState


    fun isPossibleToSave() : Boolean{
        val isFirstname = firstname.length > 1 && firstname.isNotBlank()
        val isLastname = lastname.length> 1 &&  lastname.isNotBlank()
        val isMiddleName = middleName.length>1 && middleName.isNotBlank()
        val isBirthday = birthday.isNotBlank()
        val isEdit = (this as? ChangingPatientRecord)?.isEdit ?: true
        return isFirstname && isLastname &&  isMiddleName && isBirthday && isEdit
    }
    companion object{
        fun toPatientRecordState(isCreatingPatientRecord: Boolean, userModel: UserModel) : PatientRecordState{
            if (isCreatingPatientRecord){
                return CreatingPatientRecord(
                    firstname = userModel.firstname,
                    lastname = userModel.lastname,
                    middleName = userModel.middleName,
                    birthday = userModel.birthday,
                    image = userModel.image,
                    gender = userModel.gender,
                    followingActions = CreatingPatientRecordFollowingActions.GoToMainContent
                )
            }else{
                return ChangingPatientRecord(
                    firstname = userModel.firstname,
                    lastname = userModel.lastname,
                    middleName = userModel.middleName,
                    birthday = userModel.birthday,
                    image = userModel.image,
                    gender = userModel.gender,
                    isEdit = false,
                )
            }
        }
    }
}