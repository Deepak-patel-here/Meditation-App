package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.nosleep.R

@Composable
fun TopBarSong(onClick:()-> Unit,modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        IconButton(onClick = {
            onClick()
        }, modifier = Modifier.size(42.dp)) {
            Image(
                painter = painterResource(R.drawable.back_btn),
                contentDescription = null,
                modifier = Modifier.size(42.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = {}, modifier = Modifier.size(42.dp)) {
            Image(
                painter = painterResource(R.drawable.download_song),
                contentDescription = null,
                modifier = Modifier.size(42.dp)
            )
        }
    }
    
}

