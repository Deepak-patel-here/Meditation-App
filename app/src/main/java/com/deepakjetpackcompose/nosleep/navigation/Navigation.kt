package com.deepakjetpackcompose.nosleep.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deepakjetpackcompose.nosleep.view.ChooseScreen
import com.deepakjetpackcompose.nosleep.view.HomeScreen
import com.deepakjetpackcompose.nosleep.view.LoginScreen
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen
import com.deepakjetpackcompose.nosleep.view.OnBoardingScreen2
import com.deepakjetpackcompose.nosleep.view.PlayerScreen
import com.deepakjetpackcompose.nosleep.view.SignUpScreen
import com.deepakjetpackcompose.nosleep.view.SplashScreen
import com.deepakjetpackcompose.nosleep.viewmodel.AuthState
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {

    val navController = rememberNavController()
    val authState = authViewModel.authState.collectAsState()


    NavHost(navController = navController, startDestination = NavigationHelper.SplashScreen.route) {

        composable(route = NavigationHelper.GetStarted.route) {
            OnBoardingScreen(
                authViewModel = authViewModel,
                navController = navController,
                modifier = modifier
            )
        }

        composable(route = NavigationHelper.OnBoarding.route) {
            OnBoardingScreen2(navController = navController, modifier = modifier)
        }

        composable(route = NavigationHelper.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                navController = navController,
                modifier = modifier
            )
        }

        composable(route = NavigationHelper.SignUpScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                navController = navController,
                modifier = modifier
            )
        }
        composable(route = NavigationHelper.ChooseScreen.route) {
            ChooseScreen(
                navController = navController,
                authViewModel = authViewModel,
                modifier = modifier
            )
        }

        composable(route = NavigationHelper.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                authViewModel = authViewModel,
                modifier = modifier
            )
        }

        composable(route = "${NavigationHelper.PlayerScreen.route}/{name}/{audioUrl}/{imgUrl}",
            arguments = listOf(
                navArgument (name = "name"){
                    type= NavType.StringType
                },
                navArgument (name = "audioUrl"){
                    type= NavType.StringType
                },
                navArgument (name = "imgUrl"){
                    type= NavType.StringType
                }
            )) {backStackEntry->
            val nameEncoded = backStackEntry.arguments?.getString("name") ?: ""
            val audioEncoded = backStackEntry.arguments?.getString("audioUrl") ?: ""
            val imgEncoded = backStackEntry.arguments?.getString("imgUrl") ?: ""

            val name = URLDecoder.decode(nameEncoded, StandardCharsets.UTF_8.toString())
            val audioUrl = URLDecoder.decode(audioEncoded, StandardCharsets.UTF_8.toString())
            val imgUrl = URLDecoder.decode(imgEncoded, StandardCharsets.UTF_8.toString())

            PlayerScreen(navController = navController, name = name, audioUrl = audioUrl, imgUrl = imgUrl)
        }

        composable(route = NavigationHelper.SplashScreen.route) {
            SplashScreen(navController=navController, authViewModel = authViewModel)
        }


    }

}