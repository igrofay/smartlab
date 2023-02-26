package com.example.smartlab

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.domain.repos.AppRepos
import com.example.smartlab.onboard.model.InformationApp
import com.example.smartlab.onboard.view.OnboardScreen
import com.example.smartlab.onboard.view_model.OnboardVM
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class OnboardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var viewModel: OnboardVM

    private val assertTestData = listOf(
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

    @Before
    fun createObjects(){
        val appRepos =object : AppRepos{
            override val isUserFamiliarWithApp: Boolean
                get() = false

            override fun userHasBeenIntroducedWithApp() {

            }
        }
        viewModel = OnboardVM(appRepos)
    }
    @Test
    fun displayTestInformationApp(){
        composeTestRule.setContent {
            OnboardScreen(
                goToAuthentication = { /*TODO*/ },
                viewModel = viewModel,
            )
        }
        composeTestRule.onNodeWithTag("next")
            .assertIsDisplayed()//Проверяем существует ли кнопка продолжить
        // проверка слайдов
        for (i in assertTestData.indices){
            val item = assertTestData[i]
            composeTestRule.onNodeWithTag("label")
                .assert(hasText(item.label)) // Проверям на схожость с заголовками
            composeTestRule.onNodeWithTag("description")
                .assert(hasText(item.description)) // Проверям на схожость с описанием
            composeTestRule.onNodeWithTag(item.image.toString())
                .assertIsDisplayed() //  Проверяем отображется ли картинка
            if(i == assertTestData.size.dec()){
                composeTestRule.onNodeWithTag("next")
                    .assert(hasText("Завершить"))
                // Проверяем чно на последнем слайде кнопка имеен текст завершить
            }
            composeTestRule.onNodeWithTag("next")
                .performClick() // Нажимаем кнопку продолжить
        }
    }

}