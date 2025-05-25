package com.deepakjetpackcompose.nosleep.navigation

sealed class NavigationHelper(val route:String) {
    object GetStarted : NavigationHelper(route = "getStarted")
    object OnBoarding: NavigationHelper(route = "onBoarding")
}