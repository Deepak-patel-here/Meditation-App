package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text

@Composable
fun ClickableTextAuth(text1:String,text2:String,onClick:()->Unit,modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {
        Text(text1,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = text,
            fontSize = 14.sp
        )
        Text(text2,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = text,
            fontSize = 14.sp,
            modifier = Modifier.clickable(onClick = {onClick()})
        )
    }

}