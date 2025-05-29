package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.nosleep.R


@Composable
fun PlayerGesture(onClick:()->Unit, isPlaying:MutableState<Boolean>, modifier: Modifier = Modifier) {

    Row (modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_skip_previous_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF404756)),
                modifier = Modifier.size(70.dp)
            )
        }
        Spacer(Modifier.width(20.dp))

        IconButton(onClick = {
            onClick()
        }, modifier = Modifier.size(70.dp)) {
            Image(
                painter = painterResource(if(isPlaying.value){
                    R.drawable.baseline_pause_circle_filled_24
                }else R.drawable.baseline_play_circle_filled_24 ),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF404756)),
                modifier = Modifier.size(70.dp)
            )
        }
        Spacer(Modifier.width(20.dp))

        IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_skip_next_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF404756)),
                modifier = Modifier.size(70.dp)
            )
        }
    }

}