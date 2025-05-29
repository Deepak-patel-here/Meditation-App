package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnBoardingScreen2(modifier: Modifier = Modifier, navController: NavHostController) {
    val systemUi= rememberSystemUiController()

    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(0xFF404756),
            darkIcons = false
        )
        systemUi.setNavigationBarColor(
            color = Color(0xFFE9EFFF),
            darkIcons = true
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EFFF))
            .navigationBarsPadding()
            .statusBarsPadding()
    ){
        val screenHeight= minHeight
        val screenWidth=minWidth

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (topBg,man,moon,title,join,login,signup)=createRefs()

            Image(
                painter = painterResource(R.drawable.vector_1),
                contentDescription = null,
                modifier = Modifier
                    .height(screenHeight*0.3f)
                    .width(screenWidth*1.4f)
                    .constrainAs (topBg){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillBounds
            )

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

            Text(
                "Want to Join Us?",
                fontSize = 22.sp,
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = text,
                modifier = Modifier.constrainAs(join) {
                    top.linkTo(topBg.bottom, margin = 25.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Image(
                painter = painterResource(R.drawable.man_1),
                contentDescription = null,
                modifier = Modifier
                .size(screenWidth * 1f)
                .constrainAs(man) {
                    top.linkTo(join.bottom, margin = 25.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

            )

            OutlinedButton(onClick = {
                navController.navigate(NavigationHelper.LoginScreen.route){
                    popUpTo(NavigationHelper.OnBoarding.route){inclusive=true}
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .constrainAs (login){
                        top.linkTo(man.bottom, margin = -30.dp)
                    },
                border = BorderStroke(width = 2.dp, color = text),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("Login",
                    fontFamily = SyneBold,
                    fontWeight = FontWeight.Bold,
                    color = text,
                    fontSize = 14.sp
                    )
            }

            Text("sign up",
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = text,
                fontSize = 13.sp,
                modifier = Modifier.constrainAs (signup){
                    top.linkTo(login.bottom, margin = 10.dp)
                    start.linkTo(login.start)
                    end.linkTo(login.end)
                }
                    .clickable(onClick = {
                        navController.navigate(NavigationHelper.SignUpScreen.route){
                            popUpTo(NavigationHelper.OnBoarding.route){inclusive=true}
                        }
                    })
                )
        }
    }

}