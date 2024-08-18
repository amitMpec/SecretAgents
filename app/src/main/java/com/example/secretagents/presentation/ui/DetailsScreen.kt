package com.example.secretagents.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.secretagents.R
import com.example.secretagents.ui.theme.SurfaceContainerHigh
import com.example.secretagents.ui.theme.SurfaceLight
import com.example.secretagents.ui.theme.TextColorLow

@Composable
fun DetailsScreen(
    name: String? = "",
    email: String? = "amit@gmail.com",
    picture: String? = "",
    location: String? = "",
    dob: String? = "",
    phone: String? = "",
    age: String? = "",
    gender: String? = "",
    coordinates: String? = "",
    nationality: String? = ""
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceContainerHigh),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(picture),
                contentDescription = null,
                modifier = Modifier
                    .size(134.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(
                    start = 16.dp, top = 21.dp, end = 16.dp, bottom = 21.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name!!,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    //    text = user.email,
                    text = email!!,
                    color = TextColorLow,
                    modifier = Modifier.padding(top = 3.dp),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontWeight = FontWeight.Light,
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(418.dp)
                    //.shadow(0.5.dp, RoundedCornerShape(12.dp))
                    .background(SurfaceLight, RoundedCornerShape(12.dp))
                    .clickable { /* Handle click */ }, contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    showData("Gender ", gender)
                    showData("Location", location)
                    showData("Coordinates", coordinates)
                    showData("DOB", dob)
                    showData("Age", age)
                    showData("Phone", phone)
                    showData("Nationality", nationality)
                }
            }
        }
    }
}


@Composable
fun showData(title: String, data: String? = "") {
    Row(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 11.dp,
            end = 16.dp,
        ), horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$title : ",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = data ?: "",
            color = TextColorLow,
            modifier = Modifier.padding(top = 3.dp),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight.Light,
        )
    }
}