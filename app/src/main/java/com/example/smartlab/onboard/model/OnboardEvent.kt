package com.example.smartlab.onboard.model

sealed class OnboardEvent {
    object NextSlide: OnboardEvent()
}