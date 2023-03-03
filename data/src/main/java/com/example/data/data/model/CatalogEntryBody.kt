package com.example.data.data.model

import com.example.data.domain.model.catalog.CatalogEntryModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CatalogEntryBody(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val price: String,
    override val category: String,
    @SerialName("time_result")
    override val timeResult: String,
    override val preparation: String,
    override val bio: String,
): CatalogEntryModel