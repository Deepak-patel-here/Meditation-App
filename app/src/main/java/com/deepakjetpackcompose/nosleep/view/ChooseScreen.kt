package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text
import com.deepakjetpackcompose.nosleep.util.ChooseComposable
import com.deepakjetpackcompose.nosleep.util.ChooseText

@Preview
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ChooseScreen(modifier: Modifier = Modifier) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2DCC2))
            .navigationBarsPadding()
            .statusBarsPadding()
    ){
        val screenHeight= minHeight
        val screenWidth=minWidth



        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val (bg,title,list,btn)=createRefs()
            
            Image(painter = painterResource(R.drawable.bg1),
                contentDescription = null,
            modifier= Modifier.fillMaxSize()
                .constrainAs (bg){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                contentScale = ContentScale.FillBounds
            )

            ChooseText(modifier=Modifier.constrainAs(title) {
                start.linkTo(parent.start,margin=20.dp)
                top.linkTo(parent.top,margin=20.dp)
            })

            ChooseComposable(modifier=Modifier.constrainAs (list){
                start.linkTo(parent.start)
                top.linkTo(title.bottom,margin=20.dp)
            }.padding(horizontal = 10.dp).navigationBarsPadding())

            FloatingActionButton(onClick = {},
                modifier= Modifier.constrainAs (btn){
                    bottom.linkTo(parent.bottom, margin = 35.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text("continue",
                    fontSize = 18.sp,
                    fontFamily = SyneBold,
                    fontWeight = FontWeight.Bold,
                    color= text,
                    modifier=Modifier.padding(10.dp)
                )
            }
        }
    }

}