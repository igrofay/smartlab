package com.example.smartlab.onboard.model

internal sealed class OnboardState {
    data class DisplayInformationApp(
        val count: Int,
        val current: Int,
        val informationApp: InformationApp,
        val isEnd: Boolean = current == count.dec(),
    ): OnboardState()
    object FinishWatching : OnboardState()
}