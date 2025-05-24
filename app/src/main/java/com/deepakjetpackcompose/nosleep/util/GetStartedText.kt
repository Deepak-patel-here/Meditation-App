package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold

@Composable
fun GetStartedText(modifier: Modifier = Modifier) {

    Column (modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Hi, Welcome",
            fontSize = 32.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFCD93))
        Text("To NoSleep",
            letterSpacing = 1.5.sp,
            fontSize = 28.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFFFCD93))

        Spacer(Modifier.height(5.dp))
        Text("Get started with app where you",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Normal,
            color=Color.White)

        Text("can find some peace",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Normal,
            color=Color.White)
    }

}