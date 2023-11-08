package com.emha.jagasa.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emha.jagasa.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageProfile()
            Info()
        }
    }
}

@Composable
fun ImageProfile() {
    Surface(
        modifier = Modifier
            .size(154.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "profile image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Info() {
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Syafiq Fajrian Emha"
        )
        Text(
            fontSize = 14.sp,
            text = "syafiqfajrianemha@gmail.com",
            modifier = Modifier.padding(3.dp)
        )
    }
}
