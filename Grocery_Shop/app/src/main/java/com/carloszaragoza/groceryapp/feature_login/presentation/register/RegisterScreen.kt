package com.carloszaragoza.groceryapp.feature_login.presentation.register

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
import com.carloszaragoza.groceryapp.feature_login.presentation.register.util.RegisterEvents
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    viewModel:RegisterViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate)-> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val (focusRequester) = FocusRequester.createRefs()
    val (focusRequester2) = FocusRequester.createRefs()



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
                    Text("Register",
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
                image = R.drawable.register_image,
                contentDescription = "Register Image")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextFieldCustom(
                    text = viewModel.userText.value,
                    onTextChanged = {usr->
                        viewModel.onEvent(RegisterEvents.OnUsernameChange(usr))
                    },
                    placeholder = "UserName",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.large),
                    userValidate = viewModel.conditionUser.value,
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester2.requestFocus() }
                    ),
                    imeAction = ImeAction.Next)

                Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
                PasswordTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester2)
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.large),
                    text = viewModel.userPassword.value,
                    onTextChanged = {password->
                        viewModel.onEvent(RegisterEvents.OnPasswordChange(password))
                    },
                    placeHolder = "Password",
                    passwordValidate = viewModel.conditionPassword.value,
                    keyboardAction = KeyboardActions(

                            onNext = { focusRequester.requestFocus() }

                    ),
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.small))
                PasswordTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.large),
                    text = viewModel.userConfirmPassword.value,
                    onTextChanged = {password->
                        viewModel.onEvent(RegisterEvents.OnConfirmPasswordChange(password))
                    },
                    placeHolder = "Confirm Password",
                    passwordValidate = viewModel.conditionPassword.value,
                    keyboardAction = KeyboardActions(
                        onDone = {
                            viewModel.onEvent(RegisterEvents.OnRegister)
                        }
                    ),
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
                ButtonCustom(
                    text = "Register in",
                    onAction = {
                        viewModel.onEvent(RegisterEvents.OnRegister)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.extraLarge),
                )

                Row{
                    Text("Do you have an account?",style = TextStyle(
                        color = MaterialTheme.colors.onBackground
                    )
                    )
                    Text(" Login here",style = TextStyle(
                        color = MaterialTheme.colors.onBackground.copy(.5f)),
                        modifier = Modifier
                            .clickable{
                                viewModel.onEvent(RegisterEvents.OnNavigate(Routes.LOGIN.name))
                            })
                }

            }
        }


    }
}

