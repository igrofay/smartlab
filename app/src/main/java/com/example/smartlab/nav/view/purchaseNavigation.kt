package com.example.smartlab.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.smartlab.cart.view.CartScreen
import com.example.smartlab.nav.model.PurchaseRouting
import com.example.smartlab.nav.model.StartRouting

internal fun NavGraphBuilder.purchaseNavigation(navController: NavController) {
    navigation(PurchaseRouting.Cart.route, PurchaseRouting.route) {
        composable(PurchaseRouting.Cart.route){
            CartScreen(
                goToBack = {navController.popBackStack()},
                goToOrdering = {navController.navigate(PurchaseRouting.Ordering.route)}
            )
        }
        composable(PurchaseRouting.Ordering.route){

        }
    }
}