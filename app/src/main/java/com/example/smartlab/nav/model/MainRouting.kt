package com.example.smartlab.nav.model

import androidx.annotation.DrawableRes
import com.example.smartlab.R

internal sealed class MainRouting(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Analyzing: MainRouting("analyzing", "Анализы", R.drawable.ic_analyzing)
    object Results: MainRouting("results", "Результаты", R.drawable.ic_results)
    object Support: MainRouting("support", "Поддержка", R.drawable.ic_support)
    object Profile: MainRouting("profile", "Профиль", R.drawable.ic_profile)
    companion object{
        const val route = "main"
        val items = listOf(Analyzing, Results, Support, Profile,)
    }
}