package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE)

    var firstName by remember {
        mutableStateOf(sharedPreferences.getString("firstName", "") ?: "")
    }

    var lastName by remember {
        mutableStateOf(sharedPreferences.getString("lastName", "") ?: "")
    }

    var emailAddress by remember {
        mutableStateOf(sharedPreferences.getString("emailAddress", "") ?: "")
    }

    fun onClick() {
        sharedPreferences.edit { clear() }
        navController.navigate(Destinations.ONBOARDING)
    }

    Column {
        Header()
        Form(
            firstName = firstName,
            lastName = lastName,
            emailAddress = emailAddress,
            setFirstName = { firstName = it },
            setLastName = { lastName = it },
            setEmailAddress = { emailAddress = it },
            buttonProps = object: ButtonProps {
                override val text = "Log out"
                override val onClick = { onClick() }
            }
        )
    }
}
