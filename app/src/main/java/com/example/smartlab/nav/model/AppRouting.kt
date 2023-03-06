package com.example.smartlab.nav.model

internal sealed class AppRouting(val route:String){
    object Cart : AppRouting("cart")
}