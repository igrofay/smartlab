package com.example.smartlab.onboard.model

internal sealed class OnboardEvent {
    object NextSlide: OnboardEvent()
}