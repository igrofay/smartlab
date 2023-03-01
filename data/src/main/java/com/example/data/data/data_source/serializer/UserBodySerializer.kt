package com.example.data.data.data_source.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.data.data.model.UserBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserBodySerializer @Inject constructor() : Serializer<UserBody?> {
    val fileName= "user.preferences_pb"
    override val defaultValue: UserBody?
    get() = null

    override suspend fun readFrom(input: InputStream): UserBody? {
        try {
            return Json.decodeFromString(
                UserBody.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read Settings", serialization)
        }
    }

    override suspend fun writeTo(body: UserBody?, output: OutputStream) {
        body ?: return
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(UserBody.serializer(), body)
                    .encodeToByteArray()
            )
        }
    }
}