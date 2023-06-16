package com.celeste.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.celeste.scaffold.model.Routes
import com.celeste.scaffold.navigation.Screen1
import com.celeste.scaffold.navigation.Screen2
import com.celeste.scaffold.navigation.Screen3
import com.celeste.scaffold.navigation.Screen4
import com.celeste.scaffold.navigation.Screen5
import com.celeste.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                //ScaffoldView()
                //NavigationView()
                Column {
                    ColorAnimationSimple()
                    SizeAnimation()
                    VisibilityAnimation()
                    CrossFadeExampleAnimation()
                }
            }
        }
    }
}

@Composable
fun NavigationView() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.Screen1.route
    ) {
        composable(Routes.Screen1.route) {
            Screen1(navigationController)
        }

        composable(Routes.Screen2.route) {
            Screen2(navigationController)
        }

        composable(Routes.Screen3.route) {
            Screen3(navigationController)
        }

        composable(
            Routes.Screen4.route,
            arguments = listOf(navArgument("age") {
                type = NavType.IntType
            })
        ) {
            Screen4(
                navigationController,
                it.arguments?.getInt("age") ?: 0
            )
        }

        composable(
            Routes.Screen5.route,
            arguments = listOf(navArgument("name") {
                defaultValue = "Programador"
            })
        ) {
            Screen5(
                navigationController,
                it.arguments?.getString("name").orEmpty()
            )
        }
    }
}