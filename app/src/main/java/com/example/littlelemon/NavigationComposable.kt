package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {

    val sharedPreferences = LocalContext.current.getSharedPreferences("data", MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("emailAddress", "")

    val isAuthorized =
        firstName?.isNotEmpty() == true && lastName?.isNotEmpty() == true && email?.isNotEmpty() == true

    val startDestination = if (isAuthorized) Destinations.HOME else Destinations.ONBOARDING

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.ONBOARDING) {
            Onboarding(navController = navController)
        }
        composable(Destinations.HOME) {
            Home(navController = navController)
        }
        composable(Destinations.PROFILE) {
            Profile(navController = navController)
        }
    }
}