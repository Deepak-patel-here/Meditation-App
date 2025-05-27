package com.deepakjetpackcompose.nosleep.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepakjetpackcompose.nosleep.view.ChooseScreen
import com.deepakjetpackcompose.nosleep.view.HomeScreen
import com.deepakjetpackcompose.nosleep.view.LoginScreen
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen2
import com.deepakjetpackcompose.nosleep.view.SignUpScreen
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel

@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {

   val navController= rememberNavController()
    NavHost(navController = navController, startDestination = NavigationHelper.GetStarted.route) {

        composable (route = NavigationHelper.GetStarted.route){
            OnBoardingScreen(authViewModel=authViewModel,navController = navController, modifier = modifier)
        }

        composable (route = NavigationHelper.OnBoarding.route){
            OnBoardingScreen2(navController = navController, modifier = modifier)
        }

        composable (route = NavigationHelper.LoginScreen.route){
            LoginScreen(authViewModel=authViewModel,navController = navController, modifier = modifier)
        }

        composable (route = NavigationHelper.SignUpScreen.route){
            SignUpScreen(authViewModel=authViewModel,navController = navController, modifier = modifier)
        }
        composable (route = NavigationHelper.ChooseScreen.route){
            ChooseScreen( navController=navController,authViewModel=authViewModel,modifier = modifier)
        }

        composable (route = NavigationHelper.HomeScreen.route){
            HomeScreen( navController=navController,authViewModel=authViewModel,modifier = modifier)
        }


    }

}