package com.example.data.data.model

import com.example.data.domain.model.catalog.PromotionModel
import kotlinx.serialization.Serializable

@Serializable
internal data class PromotionBody(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val price: String,
    override val image: String
) : PromotionModel