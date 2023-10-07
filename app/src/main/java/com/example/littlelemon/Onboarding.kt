package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController

val greyColor = Color(133, 134, 144)

@Composable
fun Onboarding(navController: NavController) {

    val context = LocalContext.current

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var emailAddress by remember {
        mutableStateOf("")
    }

    fun saveSharedPreferences() {
        context.getSharedPreferences("data", Context.MODE_PRIVATE).edit {
            putString("firstName", firstName)
            putString("lastName", lastName)
            putString("emailAddress", emailAddress)
        }.apply {}
    }

    fun onSubmit() {
        if (firstName.isNotEmpty() && lastName.isNotEmpty() && emailAddress.isNotEmpty()) {
            saveSharedPreferences()
            Toast.makeText(
                context,
                "Registration successful!",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Destinations.HOME)
        } else {
            print("SOME TEXT")
            Toast.makeText(
                context,
                "Registration unsuccessful. Please enter all data.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column {
        Header()
        Title()
        Form(
            firstName = firstName,
            lastName = lastName,
            emailAddress = emailAddress,
            setFirstName = { value -> firstName = value },
            setLastName = { value -> lastName = value },
            setEmailAddress = { value -> emailAddress = value },
            buttonProps = object : ButtonProps {
                override val text = "Register"
                override val onClick = { onSubmit() }
            }
        )
    }
}

@Composable
private fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(72, 94, 87))
            .height(90.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Let's get to know you", color = Color.White, fontSize = 30.sp)
    }
}
