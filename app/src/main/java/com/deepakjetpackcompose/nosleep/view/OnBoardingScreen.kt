package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.buttonBg
import com.deepakjetpackcompose.nosleep.util.GetStartedText


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnBoardingScreen(navController: NavController,modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF56669D))
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        OnBoardingContent(
            screenWidth = maxWidth,
            screenHeight = maxHeight,
            navController = navController
        )
    }
}

@Composable
fun OnBoardingContent(navController: NavController,screenWidth: Dp, screenHeight: Dp) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, moon, welcome, bubbles, person, polygon, button,cloud1,cloud2,cloud3) = createRefs()

        // Title
        Text(
            "NoSleep",
            fontSize = 24.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // Moon Icon
        Image(
            painter = painterResource(R.drawable.nosleepmoon),
            contentDescription = null,
            modifier = Modifier
                .size(screenWidth * 0.15f)
                .constrainAs(moon) {
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // Welcome Text
        GetStartedText(modifier = Modifier.constrainAs(welcome) {
            top.linkTo(moon.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        // Background Bubbles
        Image(
            painter = painterResource(R.drawable.group_6),
            contentDescription = null,
            modifier = Modifier
                .height(screenHeight*0.6f)
                .width(screenWidth*1f)
                .constrainAs(bubbles) {
                    top.linkTo(welcome.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 60.dp)
                }
        )

        Image(
            painter = painterResource(R.drawable.group_9),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .constrainAs (cloud1){
                    end.linkTo(parent.end, margin = 15.dp)
                    bottom.linkTo(bubbles.top)
                }
        )
        Image(
            painter = painterResource(R.drawable.group_11),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .constrainAs (cloud3){
                    end.linkTo(parent.end, margin = 15.dp)
                    bottom.linkTo(person.bottom, margin = 50.dp)
                }
        )

        Image(
            painter = painterResource(R.drawable.group_10),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .constrainAs (cloud2){
                    start.linkTo(parent.start, margin = 15.dp)
                    top.linkTo(person.top)
                }
        )

        // Polygon shape background
        Image(
            painter = painterResource(R.drawable.polygon_1),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .constrainAs(polygon) {
                    bottom.linkTo(parent.bottom, margin = -100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(screenHeight * 0.5f)
        )

        // Foreground character/person
        Image(
            painter = painterResource(R.drawable.group_5),
            contentDescription = null,
            modifier = Modifier
                .size(screenWidth*0.65f)
                .constrainAs(person) {
                    bottom.linkTo(polygon.top, margin = (-140).dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end)
                }
        )

        Button(
            onClick = {
                navController.navigate(NavigationHelper.OnBoarding.route){
                    popUpTo(NavigationHelper.GetStarted.route){inclusive=true}
                }
            },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonBg),
            border = BorderStroke(2.dp, Color(0xFF404756)),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .constrainAs(button) {
                    bottom.linkTo(polygon.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Get Started",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = SyneBold,
                    color = Color(0xFF404756)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(R.drawable.rightarrow),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF404756)
                )
            }
        }
    }
}
