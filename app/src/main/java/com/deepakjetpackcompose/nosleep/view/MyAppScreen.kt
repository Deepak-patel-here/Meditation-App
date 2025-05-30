package com.deepakjetpackcompose.nosleep.view

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.util.BottomBar
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel

@Composable
fun MyAppScreen(authViewModel: AuthViewModel,
                navController: NavHostController,modifier: Modifier = Modifier) {
    val isSelected = remember { mutableStateOf(1) }
    Scaffold(
        bottomBar = {
            BottomBar(isSelected = isSelected)
        },
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { innerPadding ->

        if(isSelected.value ==1){
            HomeScreen(modifier = Modifier.padding(innerPadding),authViewModel=authViewModel,navController=navController)
        }else{
            ProfileScreen(navController=navController, authViewModel = authViewModel, modifier = Modifier.padding(innerPadding))
        }
    }

}