package com.example.smartlab.onboard.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.domain.repos.AppRepos
import com.example.smartlab.R
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.onboard.model.InformationApp
import com.example.smartlab.onboard.model.OnboardEvent
import com.example.smartlab.onboard.model.OnboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class OnboardVM @Inject constructor(
    private val appRepos: AppRepos,
) : ViewModel(), EventBase<OnboardEvent> {
    private val list = listOf(
        InformationApp(
            "Анализы",
            "Экспресс сбор и получение проб",
            R.drawable.im_analyzes,
        ),
        InformationApp(
            "Уведомления",
            "Вы быстро узнаете о результатах",
            R.drawable.im_notifications
        ),
        InformationApp(
            "Мониторинг",
            "Наши врачи всегда наблюдают за вашими показателями здоровья",
            R.drawable.im_monitoring,
        )
    )
    private val _state = mutableStateOf<OnboardState>(
        OnboardState.DisplayInformationApp(list.size, 0, list.first())
    )
    val state: State<OnboardState> by ::_state


    override fun onEvent(event: OnboardEvent) {
        when (event) {
            OnboardEvent.NextSlide -> {
                val displayInformationApp =
                    _state.value as? OnboardState.DisplayInformationApp ?: return
                if (displayInformationApp.isEnd){
                    appRepos.userHasBeenIntroducedWithApp()
                    _state.value = OnboardState.FinishWatching
                    return
                }
                val nextInformationApp = displayInformationApp.current + 1
                _state.value = OnboardState.DisplayInformationApp(
                    list.size,
                    nextInformationApp,
                    list[nextInformationApp]
                )
            }
        }
    }


}