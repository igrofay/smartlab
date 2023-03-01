package com.example.data.domain.repos

import com.example.data.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepos {
    fun getUserModel() : Flow<UserModel?>
    suspend fun updateUserModel(userModel: UserModel)
}