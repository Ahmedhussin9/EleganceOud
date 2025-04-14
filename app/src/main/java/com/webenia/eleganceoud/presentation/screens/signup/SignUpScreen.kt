package com.webenia.eleganceoud.presentation.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.webenia.eleganceoud.presentation.composables.UnderLinedEditText


@Composable
fun SignUpScreenSetup(
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    SignUpScreenContent(state = viewModel.uiState, onEvent = viewModel::onEvent)

}

@Composable
fun SignUpScreenContent(
    state: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit
) {

    val focusRequesterName = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPhone = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterConfirmPassword = remember { FocusRequester() }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        UnderLinedEditText(
            state = state.name,
            filedName = "Name",
            onValueChange = { onEvent(SignUpEvent.NameChanged(it)) },
            focusRequester = focusRequesterName,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = {
                focusRequesterEmail.requestFocus()
            })
        )

        UnderLinedEditText(
            state = state.email,
            filedName = "Email",
            onValueChange = { onEvent(SignUpEvent.EmailChanged(it)) },
            focusRequester = focusRequesterEmail,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = {
                focusRequesterPhone.requestFocus()
            })
        )

        UnderLinedEditText(
            state = state.phone,
            filedName = "Phone",
            onValueChange = { onEvent(SignUpEvent.PhoneChanged(it)) },
            focusRequester = focusRequesterPhone,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = {
                focusRequesterPassword.requestFocus()
            })
        )

        UnderLinedEditText(
            state = state.password,
            filedName = "Password",
            isPasswordField = true,
            onValueChange = { onEvent(SignUpEvent.PasswordChanged(it)) },
            focusRequester = focusRequesterPassword,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(onNext = {
                focusRequesterConfirmPassword.requestFocus()
            })
        )

        UnderLinedEditText(
            state = state.confirmPassword,
            filedName = "Confirm Password",
            isPasswordField = true,
            onValueChange = { onEvent(SignUpEvent.ConfirmPasswordChanged(it)) },
            focusRequester = focusRequesterConfirmPassword,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onEvent(SignUpEvent.Submit)
            })
        )

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignUpScreenContentPreview() {
    SignUpScreenContent(state = SignUpUiState(), onEvent = {})
}