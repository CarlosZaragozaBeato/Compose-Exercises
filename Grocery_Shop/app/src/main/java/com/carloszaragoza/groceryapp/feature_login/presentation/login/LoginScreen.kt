package com.carloszaragoza.groceryapp.feature_login.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.R
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_login.presentation.login.components.ButtonCustom
import com.carloszaragoza.groceryapp.feature_login.presentation.login.components.ImageCustom
import com.carloszaragoza.groceryapp.feature_main.presentation.screens.components.PasswordTextField
import com.carloszaragoza.groceryapp.feature_login.presentation.login.components.TextFieldCustom
import com.carloszaragoza.groceryapp.feature_login.presentation.login.util.LoginEvents
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    onNavigate:(UiEvent.OnNavigate) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {


    val scaffoldState = rememberScaffoldState()
    val (focusRequester) = FocusRequester.createRefs()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.OnNavigate -> onNavigate(event)
                else -> Unit
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar (
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {
                    Text("Login",
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground)
                }
            )
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            ImageCustom(modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
                image = R.drawable.login_image,
                contentDescription = "Login Image")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextFieldCustom(
                    text = viewModel.userText.value,
                    onTextChanged = {username->
                        viewModel.onEvent(LoginEvents.OnUsernameChange(username))
                    },
                    placeholder = "UserName",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.large),
                    userValidate = viewModel.conditionUser.value,
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    ),
                    imeAction = ImeAction.Next
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
                PasswordTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.large),
                    text = viewModel.userPassword.value,
                    onTextChanged = {password->
                        viewModel.onEvent(LoginEvents.OnPasswordChange(password))
                    },
                    placeHolder = "Password",
                    passwordValidate = viewModel.conditionPassword.value,
                    keyboardAction = KeyboardActions(
                        onDone = {
                            viewModel.onEvent(LoginEvents.OnLogin)
                        }
                    ),
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
                ButtonCustom(
                    text = "Log in",
                    onAction = {
                               viewModel.onEvent(LoginEvents.OnLogin)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.extraLarge),
                )

                Row{
                    Text("Dont you have an account?",style = TextStyle(
                        color = MaterialTheme.colors.onBackground
                    ))
                    Text(" Sign up here",style = TextStyle(
                        color = MaterialTheme.colors.onBackground.copy(.5f)),
                        modifier = Modifier
                            .clickable{
                                viewModel.onEvent(LoginEvents.OnNavigate(Routes.REGISTER.name))
                            })
                }

            }
        }


    }
}