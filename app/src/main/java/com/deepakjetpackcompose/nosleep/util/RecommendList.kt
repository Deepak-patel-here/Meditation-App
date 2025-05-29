package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deepakjetpackcompose.nosleep.model.Audios
import com.deepakjetpackcompose.nosleep.viewmodel.AuthState
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel

@Composable
fun RecommendList(navController: NavController,authViewModel: AuthViewModel, modifier: Modifier = Modifier) {
    val audiosList = remember { mutableStateOf<List<Audios>>(emptyList()) }
    val isLoading = authViewModel.isLoading.collectAsState()
    LaunchedEffect(Unit) {
        authViewModel.fetchRecommendAudios {
            audiosList.value = it
        }
    }

    if (isLoading.value is AuthState.Loading) {
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(3) {
                Column {
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .height(130.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerEffect()
                    )
                    Spacer(Modifier.height(9.dp))
                    Box(modifier.width(160.dp).height(9.dp).shimmerEffect()) { }
                }

                Spacer(Modifier.width(15.dp))

            }
        }
    } else {
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(audiosList.value) { item ->
                RecommendComp(audios = item,
                    navController = navController)
                Spacer(Modifier.width(15.dp))
            }
        }
    }


}