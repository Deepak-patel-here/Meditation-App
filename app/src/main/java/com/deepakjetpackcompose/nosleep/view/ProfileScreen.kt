package com.deepakjetpackcompose.nosleep.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    val name=remember{ mutableStateOf("") }
    val email=remember{ mutableStateOf("") }
    val pref=remember{ mutableStateOf<List<String>>(emptyList()) }
    val systemUi= rememberSystemUiController()

    LaunchedEffect(Unit) {
        authViewModel.getUserData { user->
            if(user!=null){
                name.value=user.name
                email.value=user.email
                pref.value=user.preferences
            }
        }
    }

    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(0xFF56669D),
            darkIcons = false
        )
        systemUi.setNavigationBarColor(
            color = Color(0xFF56669D),
            darkIcons = false
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF56669D))
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.musicboy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.height(20.dp))
        Text(
            name.value,
            fontSize = 24.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            email.value,
            fontSize = 24.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Spacer(Modifier.height(10.dp))
        Text(
            "Your preferences :",
            fontSize = 24.sp,
            fontFamily = SyneBold,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        pref.value.forEach {p->
            Text(
                p,
                fontSize = 24.sp,
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

    }

}