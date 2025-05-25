package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold

@Composable
fun ChooseText(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.wrapContentSize()) {
        Text(
            "What you need ",
            fontSize = 26.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF56669D),
            modifier = Modifier
        )
        Text(
            "to improve?",
            fontSize = 26.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
        )
        Text(
            "Choose things that you need to improve",
            fontSize = 10.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF727E99),
            modifier = Modifier
        )
    }

}