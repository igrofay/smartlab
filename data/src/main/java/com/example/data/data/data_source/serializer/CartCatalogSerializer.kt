package com.example.data.data.data_source.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.data.data.model.CatalogEntryBody
import com.example.data.domain.model.catalog.Ratio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CartCatalogSerializer @Inject constructor() : Serializer<List<Ratio<CatalogEntryBody>>> {
    val fileName = "cart_catalog.preferences_pb"
    override val defaultValue: List<Ratio<CatalogEntryBody>>
        get() = listOf()

    override suspend fun readFrom(input: InputStream): List<Ratio<CatalogEntryBody>> {
        try {
            return Json.decodeFromString(
                input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read Settings", serialization)
        }
    }

    override suspend fun writeTo(t: List<Ratio<CatalogEntryBody>>, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(t)
                    .encodeToByteArray()
            )
        }
    }
}