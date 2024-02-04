package com.example.labdiary.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.labdiary.view.screens.AddDisScreen
import com.example.labdiary.view.screens.AddLabScreen
import com.example.labdiary.view.screens.CompletedLabsScreen
import com.example.labdiary.view.screens.MainScreen
import com.example.labdiary.view.screens.SettingsScreen

private const val TAG = "MainNavGraph"

@Composable
fun MyNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ) {
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.AddLab.route) {
            AddLabScreen(navController = navController)
        }
        composable(route = Screens.AddDis.route) {
            AddDisScreen(navController = navController)
        }
        composable(route = Screens.Completed.route) {
            CompletedLabsScreen(navController = navController)
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}