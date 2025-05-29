package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.model.Audios
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.NunitoFont
import java.net.URLEncoder

@Composable
fun RecommendComp(navController: NavController,audios: Audios, modifier: Modifier = Modifier) {
    val encodedTitle = URLEncoder.encode(audios.title, "UTF-8")
    val encodedAudio = URLEncoder.encode(audios.audioUrl, "UTF-8")
    val encodedImg = URLEncoder.encode(audios.img, "UTF-8")

    Column(modifier = Modifier.width(180.dp).wrapContentHeight().clickable(onClick = {navController.navigate("${NavigationHelper.PlayerScreen.route}/$encodedTitle/$encodedAudio/$encodedImg")}),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Box(modifier = Modifier.width(180.dp).height(130.dp).clip(RoundedCornerShape(10.dp))){
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
        }
        Spacer(Modifier.height(6.dp))

        Text(audios.title,
            color = Color.White,
            fontFamily = NunitoFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp)
    }

}