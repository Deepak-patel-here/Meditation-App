package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.NunitoFont
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.util.BottomBar
import com.deepakjetpackcompose.nosleep.util.NewAudios
import com.deepakjetpackcompose.nosleep.util.RecentList
import com.deepakjetpackcompose.nosleep.util.RecommendList
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val isSelected = remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
        BottomBar(isSelected = isSelected)
    },
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
        ) { innerPadding ->


        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF56669D))
                .padding(horizontal = 20.dp)
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(innerPadding)
        ) {
            val screenHeight = minHeight
            val screenWidth = minWidth

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (title, logo, recent, recentText, recentImg, recommendedText, recomImg) = createRefs()

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
                        .constrainAs(logo) {
                            top.linkTo(title.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    "Recent",
                    color = Color.White,
                    fontFamily = NunitoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .constrainAs(recent) {
                            start.linkTo(parent.start)
                            top.linkTo(logo.bottom, margin = 30.dp)
                        }
                )

                Text(
                    "Complete the courses you started",
                    color = Color.White,
                    fontFamily = NunitoFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .constrainAs(recentText) {
                            start.linkTo(parent.start)
                            top.linkTo(recent.bottom)
                        }
                )

                RecentList(modifier = Modifier.fillMaxWidth().constrainAs(recentImg) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(recentText.bottom, margin = 20.dp)
                }, authViewModel = authViewModel)

                Text(
                    "Recommended for you", color = Color.White,
                    fontFamily = NunitoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .constrainAs(recommendedText) {
                            start.linkTo(parent.start)
                            top.linkTo(recentImg.bottom, margin = 30.dp)
                        })

                RecommendList(
                    authViewModel = authViewModel,
                    modifier = Modifier.constrainAs(recomImg) {
                        start.linkTo(parent.start)
                        top.linkTo(recommendedText.bottom, margin = 15.dp)
                    })


            }
        }
    }


}