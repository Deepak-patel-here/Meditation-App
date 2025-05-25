package com.deepakjetpackcompose.nosleep.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.navigation.NavigationHelper
import com.deepakjetpackcompose.nosleep.ui.theme.SyneBold
import com.deepakjetpackcompose.nosleep.ui.theme.text
import com.deepakjetpackcompose.nosleep.util.ClickableTextAuth


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EFFF))
            .navigationBarsPadding()
            .statusBarsPadding()
    ){
        val screenHeight= minHeight
        val screenWidth=minWidth

        ConstraintLayout (
            modifier = Modifier.fillMaxSize()
        ){
            val(topBg,sign,email,password,account,btn,title,name,confirmPassword)=createRefs()
            var emailText by remember { mutableStateOf("") }
            var nameText by remember { mutableStateOf("") }
            var passwordText by remember { mutableStateOf("") }
            var confirmPasswordText by remember { mutableStateOf("") }
            val passwordRequester = remember { FocusRequester() }
            val confirmPasswordRequester = remember { FocusRequester() }
            val emailRequester = remember { FocusRequester() }
            val keyboard= LocalSoftwareKeyboardController.current
            var isShow by remember { mutableStateOf(false) }

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
                "Sign Up",
                fontSize = 30.sp,
                fontFamily = SyneBold,
                fontWeight = FontWeight.Bold,
                color = text,
                modifier = Modifier.constrainAs(sign) {
                    top.linkTo(topBg.bottom, margin =( -50).dp)
                    start.linkTo(parent.start, margin = 20.dp)

                }
            )

            OutlinedTextField(
                value = nameText,
                onValueChange = {nameText=it},
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
                    .constrainAs (name){
                        top.linkTo(sign.bottom, margin = 25.dp)
                    },
                label = {
                    Text("Name",
                        fontFamily = SyneBold,
                        fontWeight = FontWeight.Bold,
                        color = text
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        emailRequester.requestFocus()
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
                value = emailText,
                onValueChange = {emailText=it},
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email),
                        contentDescription = null,
                        modifier= Modifier.size(30.dp),
                        tint = text
                    )
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .focusRequester(emailRequester)
                    .padding(horizontal = 20.dp)
                    .constrainAs (email){
                        top.linkTo(name.bottom, margin = 15.dp)
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                       confirmPasswordRequester.requestFocus()
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

            OutlinedTextField(
                value = confirmPasswordText,
                onValueChange = {confirmPasswordText=it},
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
                    .focusRequester(confirmPasswordRequester)
                    .constrainAs (confirmPassword){
                        top.linkTo(password.bottom, margin = 15.dp)
                    },
                label = {
                    Text("Confirm Password",
                        fontFamily = SyneBold,
                        fontWeight = FontWeight.Bold,
                        color = text
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
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
                navController.navigate(NavigationHelper.ChooseScreen.route)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .constrainAs (btn){
                        top.linkTo(confirmPassword.bottom, margin = 30.dp)
                    },
                border = BorderStroke(width = 2.dp, color = text),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("Sign up",
                    fontFamily = SyneBold,
                    fontWeight = FontWeight.Bold,
                    color = text,
                    fontSize = 14.sp
                )
            }

            ClickableTextAuth(
                text1 = "Already have an account?",
                text2="login",
                onClick = {
                    navController.navigate(NavigationHelper.LoginScreen.route){
                        popUpTo(NavigationHelper.SignUpScreen.route){inclusive=true}
                    }
                },
                modifier = Modifier.constrainAs(account) {
                    top.linkTo(btn.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }


    }

}