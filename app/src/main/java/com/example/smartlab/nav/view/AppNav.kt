package com.example.smartlab.nav.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.nav.model.MainRouting
import com.example.smartlab.nav.model.StartRouting

@Composable
internal fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = StartRouting.route){
        startNavigation(navController)
        composable(MainRouting.route){
            MainNavigation(navController)
        }
        purchaseNavigation(navController)
    }
}
internal fun NavController.navNoReturn(route: String){
    val lastRoute = currentBackStackEntry?.destination?.route ?: return
    navigate(route){
        popUpTo(lastRoute){ inclusive = true }
    }
}