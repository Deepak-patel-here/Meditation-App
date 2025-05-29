package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text
import com.deepakjetpackcompose.nosleep.util.ClickableTextAuth
import com.deepakjetpackcompose.nosleep.viewmodel.AuthState
import com.deepakjetpackcompose.nosleep.viewmodel.AuthViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val systemUi= rememberSystemUiController()

    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(0xFF404756),
            darkIcons = false
        )
        systemUi.setNavigationBarColor(
            color = Color(0xFFE9EFFF),
            darkIcons = true
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EFFF))
            .navigationBarsPadding()
            .statusBarsPadding()
    ){
        val screenHeight= minHeight
        val screenWidth=minWidth
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        val passwordRequester = remember { FocusRequester() }
        val keyboard= LocalSoftwareKeyboardController.current
        var isShow by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val authState by authViewModel.authState.collectAsState()
        val loading by authViewModel.isLoading.collectAsState()



        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val(topBg,login,email,password,account,btn,title)=createRefs()


            LaunchedEffect(authState) {
                if(authState== AuthState.Authenticated){
                    navController.navigate(NavigationHelper.HomeScreen.route){
                        popUpTo(NavigationHelper.LoginScreen.route){inclusive=true}
                    }
                }
            }

            Image(
                painter = painterResource(R.drawable.vector_1),
                contentDescription = null,
                modifier = Modifier
                    .height(screenHeight*0.3f)
                    .width(screenWidth*1.4f)
                    .constrainAs (topBg){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillBounds
            )

            Text(
                "NoSleep",
                fontSize = 24.sp,
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                "Login",
                fontSize = 30.sp,
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = text,
                modifier = Modifier.constrainAs(login) {
                    top.linkTo(topBg.bottom, margin = -50.dp)
                    start.linkTo(parent.start, margin = 20.dp)

                }
            )

            OutlinedTextField(
                value = emailText,
                onValueChange = {emailText=it},
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.user),
                        contentDescription = null,
                        modifier= Modifier.size(30.dp),
                        tint = text
                    )
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .constrainAs (email){
                    top.linkTo(login.bottom, margin = 25.dp)
                },
                label = {
                    Text("Email",
                        fontFamily = SyneBold,
                        fontWeight = FontWeight.Bold,
                        color = text
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordRequester.requestFocus()
                    }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = text,
                    focusedIndicatorColor = text,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = text
                )
            )

            OutlinedTextField(
                value = passwordText,
                onValueChange = {passwordText=it},
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock),
                        contentDescription = null,
                        modifier= Modifier.size(30.dp),
                        tint = text
                    )
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .focusRequester(passwordRequester)
                    .constrainAs (password){
                        top.linkTo(email.bottom, margin = 15.dp)
                    },
                label = {
                    Text("Password",
                        fontFamily = SyneBold,
                        fontWeight = FontWeight.Bold,
                        color = text
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onNext = {
                        keyboard?.hide()
                    }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = text,
                    focusedIndicatorColor = text,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = text
                ),
                visualTransformation =if(isShow) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            if(isShow)R.drawable.show
                            else R.drawable.hide
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                            .clickable(onClick = {isShow= !isShow}),
                        tint = text
                    )
                }
            )

            OutlinedButton(onClick = {
                if(emailText.isNotBlank() && passwordText.isNotBlank()){
                    authViewModel.login (email = emailText, password = passwordText){success,msg->
                        if(success){
                            Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
                        }else Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
                    }
                }else Toast.makeText(context,"please fill all the fields", Toast.LENGTH_LONG).show()

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .constrainAs (btn){
                        top.linkTo(password.bottom, margin = 30.dp)
                    },
                border = BorderStroke(width = 2.dp, color = text),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("Login",
                    fontFamily = SyneBold,
                    fontWeight = FontWeight.Bold,
                    color = text,
                    fontSize = 14.sp
                )
            }

            ClickableTextAuth(
                text1 = "Don't have an account?",
                text2="sign up",
                onClick = {navController.navigate(NavigationHelper.SignUpScreen.route){
                    popUpTo(NavigationHelper.LoginScreen.route){inclusive=true}
                } },
                modifier = Modifier.constrainAs(account) {
                    top.linkTo(btn.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }

        if (loading is AuthState.Loading) {
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