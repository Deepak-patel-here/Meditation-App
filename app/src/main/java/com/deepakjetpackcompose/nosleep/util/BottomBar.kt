package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.nosleep.R

@Composable
fun BottomBar(isSelected: MutableState<Int>, modifier: Modifier = Modifier) {
    Card ( modifier = Modifier.fillMaxWidth().height(60.dp), elevation = CardDefaults.elevatedCardElevation(10.dp), shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)){
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().height(60.dp).background(Color(0xFF56669D)),
        ) {
            IconButton(
                onClick = {isSelected.value=1},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isSelected.value == 1) {
                        Color.Yellow
                    } else Color(0xFF56669D)
                )

            ) {
                Icon(
                    painter = painterResource(R.drawable.home_h),
                    contentDescription = null,
                    tint =  if (isSelected.value == 1) Color.Black else Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(
                onClick = {isSelected.value=2},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isSelected.value == 2) {
                        Color.Yellow
                    } else Color(0xFF56669D)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.user),
                    contentDescription = null,
                    tint = if (isSelected.value == 2) Color.Black else Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

}