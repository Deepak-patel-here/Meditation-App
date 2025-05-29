package com.deepakjetpackcompose.nosleep.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.viewmodel.AuthState
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(authViewModel: AuthViewModel,navController: NavController,modifier: Modifier = Modifier) {
    val authState = authViewModel.authState.collectAsState()

    val nav=if(authState.value== AuthState.Authenticated) NavigationHelper.HomeScreen.route else NavigationHelper.GetStarted.route
    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate(nav){
            popUpTo(NavigationHelper.SplashScreen.route){inclusive=true}
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFf56669D)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(R.drawable.nosleepmoon),
            contentDescription = null,
            modifier = Modifier.size(150.dp))
    }
}