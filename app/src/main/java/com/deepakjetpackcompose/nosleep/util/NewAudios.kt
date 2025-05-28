package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.model.Audios
import com.deepakjetpackcompose.nosleep.ui.theme.NunitoFont
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold

@Composable
fun NewAudios(audios: Audios, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.width(280.dp).height(171.dp).clip(RoundedCornerShape(12.dp))){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(audios.img)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.landscape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Column (modifier = Modifier.fillMaxSize().padding(10.dp)){
            Spacer(Modifier.height(10.dp))
            Text(audios.title.toString(),
                color = Color.Black,
                fontFamily = SyneBold,
                fontWeight = FontWeight.SemiBold,
                fontSize = 21.sp,)
            Text(audios.subTitle.toString(),
                color = Color.Black,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,)
        }
    }

}