package com.example.data.data.model

import com.example.data.domain.model.user.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserBody(
    @SerialName("firstname")
    override val firstname: String = "",
    @SerialName("lastname")
    override val lastname: String = "",
    @SerialName("middlename")
    override val middleName: String ="",
    @SerialName("bith")
    override val birthday: String = "",
    @SerialName("image")
    override val image: String? =null,
    @SerialName("pol")
    override val gender: String = ""
): UserModel{
    companion object{
        fun fromUserModelToUserBody(userModel: UserModel) = UserBody(
            userModel.firstname,
            userModel.lastname,
            userModel.middleName,
            userModel.birthday,
            userModel.image,
            userModel.gender,
        )
    }
}