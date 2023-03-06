package com.example.smartlab.nav.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.analyzes.view.AnalyzesScreen
import com.example.smartlab.nav.model.BottomBarState
import com.example.smartlab.nav.model.MainRouting
import com.example.smartlab.patient_record.view.PatientRecordScreen

@Composable
internal fun MainNavigation(navController: NavController) {
    val bottomNavController = rememberNavController()
    CompositionLocalProvider(LocalBottomBar provides BottomBarState()) {
        val isShowBottomBar by LocalBottomBar.current.isShow
        Scaffold(
            bottomBar = {
                if(isShowBottomBar)
                    BottomBarMainNav(bottomNavController)
            }
        ) { paddingContent->
            NavHost(
                navController = bottomNavController,
                startDestination = MainRouting.Analyzing.route,
                modifier = Modifier.padding(paddingContent)
            ){
                composable(MainRouting.Analyzing.route){
                    AnalyzesScreen()
                }
                composable(MainRouting.Results.route){
                    Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
                        Icon(
                            painter = painterResource(R.drawable.ic_logo_shape),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(80.dp),
                        )
                    }
                }
                composable(MainRouting.Support.route){
                    Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
                        Icon(
                            painter = painterResource(R.drawable.ic_logo_shape),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(80.dp),
                        )
                    }
                }
                composable(MainRouting.Profile.route){
                    PatientRecordScreen()
                }
            }
        }
    }

}
internal val LocalBottomBar = compositionLocalOf{ BottomBarState() }