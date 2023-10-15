package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBanner(
    inputValue: String,
    onChangeInputValue: (value: String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Little Lemon", fontSize = 32.sp, style = TextStyle(
                color = Color(244, 206, 20)
            )
        )
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 15.dp)
            ) {
                Text(
                    text = "Chicago", fontSize = 26.sp, style = TextStyle(
                        color = Color(255, 255, 255)
                    )
                )
                Text(
                    text = "We are a family owned Mediterranian restaurant, " +
                            "focused on traditional recipes served with a modern twist",
                    fontSize = 16.sp,
                    style = TextStyle(
                        color = Color(255, 255, 255)
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
        TextField(
            value = inputValue,
            onValueChange = onChangeInputValue,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(234, 234, 234)),
            leadingIcon = {
                Icon(Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            placeholder = {
                Text(text = "Enter search phrase")
            }
        )
    }
}

@Composable
fun Home(navController: NavController) {

    var inputValue by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Header(navController)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(73, 94, 87))
        ) {
            HomeBanner(
                inputValue = inputValue,
                onChangeInputValue = {
                    inputValue = it
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(rememberNavController())
}
