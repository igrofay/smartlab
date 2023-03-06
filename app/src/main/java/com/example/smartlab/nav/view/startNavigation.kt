package com.example.smartlab.nav.view

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.smartlab.authentication.view.AuthenticationScreen
import com.example.smartlab.code_entry.view.CodeEntryScreen
import com.example.smartlab.nav.model.MainRouting
import com.example.smartlab.nav.model.StartRouting
import com.example.smartlab.onboard.view.OnboardScreen
import com.example.smartlab.patient_record.view.PatientRecordScreen
import com.example.smartlab.splash.view.SplashScreen


internal fun NavGraphBuilder.startNavigation(navController: NavController) {
    navigation(StartRouting.Splash.route, StartRouting.route) {
        composable(StartRouting.Splash.route) {
            SplashScreen(
                goToOnboard = {
                    navController.navNoReturn(StartRouting.Onboard.route)
                },
                goToAuthentication = { navController.navNoReturn(StartRouting.Authentication.route) },
                goToMainContent = {
                    navController.navNoReturn(MainRouting.route)
                },
                goToCodeEntry = {
                    navController.navNoReturn(
                        StartRouting.CodeEntry.allRoute(
                            needCreateNewCode = false
                        )
                    )
                }
            )
        }
        composable(StartRouting.Onboard.route) {
            OnboardScreen(goToAuthentication = { navController.navNoReturn(StartRouting.Authentication.route) })
        }
        composable(StartRouting.Authentication.route) {
            AuthenticationScreen(
                goToCodeEntry = {
                    navController.navNoReturn(
                        StartRouting.CodeEntry.allRoute(needCreateNewCode = true)
                    )
                }
            )
        }
        composable(
            StartRouting.CodeEntry.allRoute(),
            arguments = listOf(
                navArgument(
                    StartRouting.CodeEntry.arg1
                ) { type = NavType.BoolType }
            )
        ) {
            CodeEntryScreen(
                goToCreatePatientChart = {
                    navController.navNoReturn(
                        StartRouting.CreatingPatientRecord.allRoute(
                            needCreatePatientRecord = true
                        )
                    )
                },
                goToOpenMainContent = {
                    navController.navNoReturn(MainRouting.route)
                }
            )
        }
        composable(
            StartRouting.CreatingPatientRecord.allRoute(),
            arguments = listOf(
                navArgument(
                    StartRouting.CreatingPatientRecord.arg1
                ) { type = NavType.BoolType }
            ),
        ) {
            PatientRecordScreen(
                goToMainContent = { navController.navNoReturn(MainRouting.route)}
            )
        }
    }
}