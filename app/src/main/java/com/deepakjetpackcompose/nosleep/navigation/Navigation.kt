package com.deepakjetpackcompose.nosleep.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen2

@Composable
fun Navigation(modifier: Modifier = Modifier) {

   val navController= rememberNavController()
    NavHost(navController = navController, startDestination = NavigationHelper.GetStarted.route) {

        composable (route = NavigationHelper.GetStarted.route){
            OnBoardingScreen(navController = navController, modifier = modifier)
        }

        composable (route = NavigationHelper.OnBoarding.route){
            OnBoardingScreen2(navController = navController, modifier = modifier)
        }


    }

}