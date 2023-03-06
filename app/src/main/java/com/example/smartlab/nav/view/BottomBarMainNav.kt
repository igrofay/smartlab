package com.example.smartlab.nav.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.colorItemBottomBar
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.nav.model.MainRouting

@Composable
internal fun BottomBarMainNav(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Column{
        Divider(color = Color.Black.copy(0.1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 7.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            MainRouting.items.forEach {mainRouting ->
                ItemBottomBar(
                    label = mainRouting.label,
                    icon = mainRouting.icon,
                    selected = currentDestination?.hierarchy?.any {
                        it.route == mainRouting.route
                    } == true,
                ) {
                    navController.navigate(mainRouting.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemBottomBar(
    label:String,
    @DrawableRes icon: Int,
    selected: Boolean,
    onClick: ()->Unit
) {
    val animatorColor by animateColorAsState(
        targetValue = if(selected) MaterialTheme.colors.primary else colorItemBottomBar,
    )
    Column(
        modifier = Modifier
            .alphaClick(onClick),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp),
            tint = animatorColor
        )
        Text(
            text = label,
            fontFamily = sfProDisplayFontFamily,
            fontSize = 12.sp,
            color = animatorColor,
            fontWeight = FontWeight.W400
        )
    }
}
