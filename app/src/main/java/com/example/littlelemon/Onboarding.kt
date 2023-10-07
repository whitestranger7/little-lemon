package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
            onSubmit = { onSubmit() }
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(200.dp)
                .height(90.dp)
        )
    }
}

@Composable
fun Title() {
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

@Composable
fun FormField(value: String, onValueChange: (value: String) -> Unit, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        Column {
            Text(
                text = label,
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp),
                style = TextStyle(
                    color = greyColor,
                )
            )
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, greyColor, shape = RoundedCornerShape(5.dp))
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun Form(
    firstName: String,
    lastName: String,
    emailAddress: String,
    setFirstName: (value: String) -> Unit,
    setLastName: (value: String) -> Unit,
    setEmailAddress: (value: String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        Column {
            Text(
                text = "Personal information",
                style = TextStyle(
                    color = Color(68, 68, 89),
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 30.dp, top = 25.dp)
            )
            FormField(value = firstName, onValueChange = setFirstName, "First name")
            FormField(value = lastName, onValueChange = setLastName, "Last name")
            FormField(value = emailAddress, onValueChange = setEmailAddress, "Email")
        }
        Button(
            onClick = onSubmit,
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(244, 206, 20)
            ),
            border = BorderStroke(1.dp, Color(218, 171, 78)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Register", style = TextStyle(color = Color.Black), fontSize = 18.sp)
        }
    }
}