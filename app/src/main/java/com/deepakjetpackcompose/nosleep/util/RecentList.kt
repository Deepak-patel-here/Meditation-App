package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.nosleep.model.Recent
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel

@Composable
fun RecentList(authViewModel: AuthViewModel,modifier: Modifier = Modifier) {

    val recentList = remember { mutableStateOf<List<Recent>>(emptyList()) }
    LaunchedEffect(Unit) {
        authViewModel.fetchRecentAudio {
            recentList.value=it
        }
    }

    LazyRow (modifier = modifier.fillMaxWidth()){
        items (recentList.value){item->
            NewAudios(recent = item)
            Spacer(Modifier.width(15.dp))
        }
    }

}