package com.example.smartlab.nav.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.nav.model.StartRouting

@Composable
internal fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = StartRouting.route){
        startNavigation(navController)
    }
}