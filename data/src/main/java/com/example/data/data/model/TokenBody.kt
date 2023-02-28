package com.example.data.data.model

import com.example.data.domain.model.app.TokenModel

@kotlinx.serialization.Serializable
internal data class TokenBody(
    override val token: String
) : TokenModel