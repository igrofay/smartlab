package com.example.smartlab.nav.model

internal sealed class StartRouting(route: String) : AppRouting(route) {
    object Splash : StartRouting("splash")
    object Onboard : StartRouting("onboard")
    object Authentication : StartRouting("authentication")
    object CodeEntry: StartRouting("code_entry"){
        const val arg1 = "create_new_code"
        fun allRoute(needCreateNewCode: Boolean? = null): String{
            val argument1 = needCreateNewCode ?: "{$arg1}"
            return "$route/$argument1"
        }
    }
    object CreatingPatientRecord : StartRouting("creating_patient_record"){
        const val arg1 = "create_new_patient_record"
        fun allRoute(needCreatePatientRecord: Boolean? = null): String{
            val argument1 = needCreatePatientRecord ?: "{$arg1}"
            return "$route/$argument1"
        }
    }
    companion object{
        const val route ="start"
    }
}