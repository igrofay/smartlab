package com.example.data.data.repos

import androidx.datastore.core.DataStore
import com.example.data.data.di.AuthorizedHttpClient
import com.example.data.data.model.UserBody
import com.example.data.domain.model.user.UserModel
import com.example.data.domain.repos.UserRepos
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.JsonArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserReposImpl @Inject constructor(
    private val userDataStore: DataStore<UserBody?>,
    @AuthorizedHttpClient private val client: HttpClient,
): UserRepos {
    override fun getUserModel(): Flow<UserModel?> {
        return userDataStore.data
    }

    override suspend fun updateUserModel(userModel: UserModel) {
        val userBody = UserBody.fromUserModelToUserBody(userModel)
        val body = client.post("/api/createProfile"){
            setBody(userBody)
            contentType(ContentType.Application.Json)
        }.body<UserBody>()
        userDataStore.updateData { body }
    }
}