package com.example.smartlab.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.smartlab.authentication.view.AuthenticationScreen
import com.example.smartlab.nav.model.StartRouting
import com.example.smartlab.onboard.view.OnboardScreen
import com.example.smartlab.splash.view.SplashScreen


internal fun NavGraphBuilder.startNavigation(navController: NavController) {
    navigation(StartRouting.Splash.route, StartRouting.route){
        composable(StartRouting.Splash.route){
            SplashScreen(
                goToOnboard = { navController.navigate(StartRouting.Onboard.route) },
                goToAuthentication = { navController.navigate(StartRouting.Authentication.route) },
                goToMainContent = {  },
                goToCodeEntry = {navController.navigate(StartRouting.CodeEntry.route)}
            )
        }
        composable(StartRouting.Onboard.route){
            OnboardScreen(goToAuthentication = { navController.navigate(StartRouting.Authentication.route)  })
        }
        composable(StartRouting.Authentication.route){
            AuthenticationScreen(goToCodeEntry = { navController.navigate(StartRouting.CodeEntry.route)})
        }
        composable(StartRouting.CodeEntry.route){

        }
    }
}