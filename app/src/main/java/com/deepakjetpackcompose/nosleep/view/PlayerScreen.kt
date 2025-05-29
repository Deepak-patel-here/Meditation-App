package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.NunitoFont
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.util.PlayerGesture
import com.deepakjetpackcompose.nosleep.util.TopBarSong
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@SuppressLint("UnusedBoxWithConstraintsScope")

@Composable
fun PlayerScreen(
    navController: NavController,
    name: String,
    audioUrl: String,
    imgUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    val isPlaying = remember { mutableStateOf(false) }
    val currentPosition = remember { mutableStateOf(0) }
    val duration = remember { mutableStateOf(0) }
    val systemUi= rememberSystemUiController()

    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(0xFFFFCD93),
            darkIcons = true
        )
        systemUi.setNavigationBarColor(
            color = Color(0xFFFFCD93),
            darkIcons = true
        )
    }

    LaunchedEffect(audioUrl) {
        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                duration.value = it.duration
                it.start()
                isPlaying.value = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(isPlaying.value) {
        while (isPlaying.value) {
            currentPosition.value = mediaPlayer.currentPosition
            delay(500)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFCD93))
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
            .statusBarsPadding()

    ) {
        val screenHeight = minHeight
        val screenWidth = minWidth

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topbar, songName, play, seekbar, image) = createRefs()

            TopBarSong(
                onClick = { navController.popBackStack() },
                modifier = Modifier.constrainAs(topbar) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Text(
                text = name,
                fontSize = 30.sp,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF404756),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(songName) {
                    top.linkTo(topbar.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.landscape),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(250.dp)
                    .constrainAs(image) {
                        top.linkTo(songName.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )

            PlayerGesture(modifier = Modifier.constrainAs(play) {
                top.linkTo(image.bottom, margin = 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, onClick = {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlaying.value = false
                } else {
                    mediaPlayer.start()
                    isPlaying.value = true
                }
            }, isPlaying = isPlaying)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .constrainAs(seekbar) {
                        top.linkTo(play.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Slider(
                    value = currentPosition.value.toFloat(),
                    onValueChange = { newValue ->
                        currentPosition.value = newValue.toInt()
                    },
                    onValueChangeFinished = {
                        mediaPlayer.seekTo(currentPosition.value)
                    },
                    valueRange = 0f..(duration.value.toFloat()),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = formatTime(currentPosition.value), color = Color.DarkGray)
                    Text(text = formatTime(duration.value), color = Color.DarkGray)
                }
            }


        }


    }
}

fun formatTime(milliseconds: Int): String {
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}