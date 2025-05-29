package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.R


@Composable
fun ChooseContent(
    textTitle: String,
    img: Int,
    personImg: Int,
    bgColor: Color,
    isClicked: Boolean,
    onClick:(String)-> Unit,
    modifier: Modifier = Modifier,
) {

    ConstraintLayout(
        modifier = Modifier
            .width(155.dp)
            .clip(RoundedCornerShape(12.dp)) // Apply clip BEFORE background
            .background(bgColor)
            .clickable { onClick(textTitle) }
            .border(
                width = 1.5.dp,
                color = if (isClicked) Color.Green else bgColor,
                shape = RoundedCornerShape(12.dp) // Also apply shape here
            )
            .padding(10.dp)
    ) {
        val (bgImage, person, title) = createRefs()

        Text(
            textTitle,
            fontSize = 14.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(img),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(bgImage) {
                    top.linkTo(title.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(100.dp)
        )
        Image(
            painter = painterResource(personImg),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(person) {
                    top.linkTo(title.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(150.dp)
        )


    }

}