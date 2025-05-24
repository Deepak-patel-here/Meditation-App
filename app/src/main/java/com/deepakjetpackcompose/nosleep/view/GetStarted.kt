package com.deepakjetpackcompose.nosleep.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.util.GetStartedText

@Preview
@Composable
fun GetStarted(modifier: Modifier = Modifier) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF56669D)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.height(20.dp))

        Text("NoSleep",
            fontSize = 24.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White)
        Spacer(Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.nosleepmoon),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(Modifier.height(10.dp))
        GetStartedText()
    }

}