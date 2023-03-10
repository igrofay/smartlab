package com.example.smartlab.onboard.model

import androidx.annotation.DrawableRes

internal data class InformationApp(
    val label: String,
    val description: String,
    @DrawableRes val image:Int,
)