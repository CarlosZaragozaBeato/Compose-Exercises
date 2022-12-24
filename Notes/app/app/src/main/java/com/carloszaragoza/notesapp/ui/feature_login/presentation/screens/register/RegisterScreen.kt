package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register

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
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register.util.RegisterEvents
import com.carloszaragoza.notesapp.ui.feature_login.presentation.util.PasswordTextField
import com.carloszaragoza.notesapp.ui.feature_login.presentation.util.TextFieldCustom
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    val (focusRequester) = FocusRequester.createRefs()
    val (focusRequester2) = FocusRequester.createRefs()
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
    ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                RegisterTitle(status = viewModel.status.value)
                InputSection(
                    viewModel = viewModel,
                    focusRequester = focusRequester,
                    focusRequester2 = focusRequester2
                ){
                    viewModel.onEvent(RegisterEvents.OnNavigate(it))
                }
            }

    }

}

@Composable
fun InputSection(
        viewModel: RegisterViewModel = hiltViewModel(),
        focusRequester: FocusRequester,
        focusRequester2: FocusRequester,
        onNavigate: (String) -> Unit,
        ) {
        Column {
            TextFieldCustom(
                text = viewModel.username.value,
                onTextChanged = {viewModel.onEvent(RegisterEvents.OnUsernameChanged(it))},
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
                onTextChanged = {viewModel.onEvent(RegisterEvents.OnPasswordChanged(it))},
                passwordValidate = viewModel.conditionPassword.value,
                keyboardAction = KeyboardActions(
                    onDone = {
                        focusRequester2.requestFocus()
                    }
                ),
                imeAction = ImeAction.Next)

            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            PasswordTextField(
                text = viewModel.confirmUserPassword.value,
                modifier = Modifier
                    .focusRequester(focusRequester2),
                placeHolder = "Confirm Password",
                onTextChanged = {viewModel.onEvent(RegisterEvents.OnPasswordConfirmedChanged(it))},
                passwordValidate = viewModel.conditionConfirmPassword.value,
                keyboardAction = KeyboardActions(
                    onDone = {
                        viewModel.onEvent(RegisterEvents.OnSubmit)
                    }
                ),
                imeAction = ImeAction.Done)
            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
        }
        Button(onClick = { viewModel.onEvent(RegisterEvents.OnSubmit) },
            modifier = Modifier
                .padding(top = LocalSpacing.current.large)
                .fillMaxWidth(.6f)) {
            Text("Get Started",
                style = MaterialTheme.typography.h6,
                color = Color(0xffEEEEEE))
        }
        NotLogin {
            onNavigate(it)
        }
}

@Composable
fun NotLogin(
    onNavigate: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Already A Member?")
        TextButton(onClick = { onNavigate.invoke(Routes.LOGIN.name) }) {
            Text("Log in")
        }
    }

}


@Composable
fun RegisterTitle(status: Status?) {
    Column(
        modifier = Modifier
            .padding(bottom = LocalSpacing.current.extraLarge)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) { Text("Create Account",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground)
        if(status?.Status == null){
            Text("The server is now offline",
                fontWeight = FontWeight.Bold,
                color = Color.Red.copy(.5f))
        }

    }
}