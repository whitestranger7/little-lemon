package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Header(navController: NavController? = null) {
    Box {
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
        if (navController != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Destinations.PROFILE)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FormField(value: String, onValueChange: (value: String) -> Unit, label: String) {
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

interface ButtonProps {
    val text: String
    val onClick: () -> Unit
}

@Composable
fun Form(
    firstName: String,
    lastName: String,
    emailAddress: String,
    setFirstName: (value: String) -> Unit,
    setLastName: (value: String) -> Unit,
    setEmailAddress: (value: String) -> Unit,
    buttonProps: ButtonProps
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
            onClick = buttonProps.onClick,
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(244, 206, 20)
            ),
            border = BorderStroke(1.dp, Color(218, 171, 78)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = buttonProps.text, style = TextStyle(color = Color.Black), fontSize = 18.sp)
        }
    }
}