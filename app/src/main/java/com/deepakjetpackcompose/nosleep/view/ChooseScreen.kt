package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.model.User
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text
import com.deepakjetpackcompose.nosleep.util.ChooseComposable
import com.deepakjetpackcompose.nosleep.util.ChooseText
import com.deepakjetpackcompose.nosleep.viewmodel.AuthState
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ChooseScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    var selectedKeywords by remember { mutableStateOf<List<String>>(emptyList()) }
    val name = authViewModel.name.collectAsState()
    val email = authViewModel.email.collectAsState()
    val loading = authViewModel.isLoading.collectAsState()
    val context = LocalContext.current

    val systemUi= rememberSystemUiController()

    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(0xFFF2DCC2),
            darkIcons = false
        )
        systemUi.setNavigationBarColor(
            color = Color(0xFFF2BDBB),
            darkIcons = true
        )
    }
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2DCC2))

    ) {
        val screenHeight = minHeight
        val screenWidth = minWidth



        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (bg, title, list, btn) = createRefs()

            Image(
                painter = painterResource(R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(bg) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillBounds
            )

            ChooseText(modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start, margin = 20.dp)
                top.linkTo(parent.top, margin = 20.dp)
            })

            ChooseComposable(
                modifier = Modifier
                    .constrainAs(list) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(title.bottom, margin = 20.dp)
                    }
                    .padding(horizontal = 10.dp),
                selectedKeywords = selectedKeywords,
                onPreferenceClick = { title ->
                    selectedKeywords = if (selectedKeywords.contains(title)) {
                        selectedKeywords - title
                    } else {
                        selectedKeywords + title
                    }
                })

            FloatingActionButton(
                onClick = {
                    val userName = name
                    val userEmail = email

                    val userData = User(
                        name = userName.value.toString(),
                        email = userEmail.value.toString(),
                        preferences = selectedKeywords
                    )

                    authViewModel.saveUserToFireStore(user = userData) { success, msg ->
                        if (success) {
                            navController.navigate(NavigationHelper.MyAppScreen.route) {
                                popUpTo(NavigationHelper.ChooseScreen.route) { inclusive = true }
                            }
                        } else Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

                    }
                },
                modifier = Modifier.constrainAs(btn) {
                    bottom.linkTo(parent.bottom, margin = 35.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Icon(
                    painter=painterResource(R.drawable.rightarrow),
                    contentDescription = null,
                    modifier=Modifier.size(50.dp)
                )
            }
        }

        if (loading.value == AuthState.Loading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}