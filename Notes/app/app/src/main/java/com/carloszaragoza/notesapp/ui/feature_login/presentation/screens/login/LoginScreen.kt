package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login.util.LoginEvents
import com.carloszaragoza.notesapp.ui.feature_login.presentation.util.PasswordTextField
import com.carloszaragoza.notesapp.ui.feature_login.presentation.util.TextFieldCustom
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate:(UiEvent.Navigate) -> Unit
) {

    val (focusRequester) = FocusRequester.createRefs()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginTitle(status = viewModel.status.value)
                InputsSection(
                    viewModel = viewModel,
                    focusRequester = focusRequester
                ) {
                    viewModel.onEvent(LoginEvents.OnNavigate(it))
                }
            }
    }
}
@Composable
fun LoginTitle(status: Status? ){
        Column(
            modifier = Modifier
                .padding(bottom = LocalSpacing.current.extraLarge)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
           Column {
               Text("Hello Again!",
                   textAlign = TextAlign.Center,
                   style = MaterialTheme.typography.h4,
                   color = MaterialTheme.colors.onBackground)
               Spacer(modifier = Modifier.height(LocalSpacing.current.small))
               Text("Welcome back you've \n " +
                       "been missed!",
                   textAlign = TextAlign.Center,
                   style = MaterialTheme.typography.h6,
                   color = MaterialTheme.colors.onBackground.copy(.8f))
           }
            if(status?.Status == null){
                Text("The server is now offline",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red.copy(.5f))
            }
        }
}

@Composable
fun InputsSection(
    viewModel: LoginViewModel = hiltViewModel(),
    focusRequester: FocusRequester,
    onNavigate: (String) -> Unit,
) {
    Column {
        TextFieldCustom(
            text = viewModel.userText.value,
            onTextChanged = {viewModel.onEvent(LoginEvents.OnUsername(it))},
            placeholder = "Enter username",
            modifier = Modifier,
            userValidate = viewModel.conditionUser.value,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.small))
        PasswordTextField(
            text = viewModel.userPassword.value,
            modifier = Modifier
                .focusRequester(focusRequester),
            placeHolder = "Password",
            onTextChanged = {viewModel.onEvent(LoginEvents.OnPassword(it))},
            passwordValidate = viewModel.conditionPassword.value,
            keyboardAction = KeyboardActions(
                onDone = {
                    viewModel.onEvent(LoginEvents.OnLogin)
                }
            ),
            imeAction = ImeAction.Done)
        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
    }
    Button(onClick = { viewModel.onEvent(LoginEvents.OnLogin) },
        modifier = Modifier
            .padding(top = LocalSpacing.current.large)
            .fillMaxWidth(.6f)) {
        Text("Sign In",
            style = MaterialTheme.typography.h6,
            color = Color(0xffEEEEEE))
    }
    NotRegistered {
        onNavigate.invoke(it)
    }
}

@Composable
fun NotRegistered(
    onNavigate: (String) -> Unit
) {
       Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center,
           modifier = Modifier
               .fillMaxWidth()
       ) {
           Text("Not a member?")
            TextButton(onClick = { onNavigate.invoke(Routes.REGISTER.name) }) {
                Text("Register now")
            }
       }     
        
}
