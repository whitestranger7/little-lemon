package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import android.util.Log
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

    val isAuthorized = sharedPreferences.getString("firstName", "")
        ?.isNotEmpty() == true && sharedPreferences.getString("lastName", "")
        ?.isNotEmpty() == true && sharedPreferences.getString("emailAddress", "")
        ?.isNotEmpty() == true

    Log.d("Navigation", "isAuthorized: $firstName $lastName $email")

    val startDestination = if (isAuthorized) Destinations.HOME else Destinations.ONBOARDING

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.ONBOARDING) {
            Onboarding(navController = navController)
        }
        composable(Destinations.HOME) {
            Home()
        }
        composable(Destinations.PROFILE) {
            Profile()
        }
    }
}