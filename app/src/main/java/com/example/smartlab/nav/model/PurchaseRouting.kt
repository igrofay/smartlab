package com.example.smartlab.nav.model

internal sealed class PurchaseRouting(route: String) : AppRouting(route){
    object Cart : PurchaseRouting("cart")
    object Ordering : PurchaseRouting("ordering")
    companion object{
        const val route = "purchase"
    }
}