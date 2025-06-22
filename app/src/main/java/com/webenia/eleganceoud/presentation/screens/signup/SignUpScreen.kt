package com.webenia.eleganceoud.presentation.screens.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.composables.LoadingOverlay
import com.webenia.eleganceoud.presentation.composables.UnderLinedEditText
import com.webenia.eleganceoud.presentation.screens.signin.navigateToSignInScreen

import com.webenia.eleganceoud.ui.theme.LightGrey
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary
import com.webenia.eleganceoud.ui.theme.poppinsFamily


@Composable
fun SignUpScreenSetup(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    SignUpScreenContent(
        state = viewModel.uiState, onEvent = viewModel::onEvent,
        onSubmitClick = {
            viewModel.onEvent(
                SignUpEvent.Submit
            )
        }
    )

}

@Composable
fun SignUpScreenContent(
    state: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit,
    onSubmitClick: () -> Unit
) {
    val context = LocalContext.current

    val focusRequesterName = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPhone = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterConfirmPassword = remember { FocusRequester() }

    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(state.success) {
        if (state.success) {
            Toast.makeText(context, "Done ya kaber", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it.asString(context), Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .align(CenterHorizontally)
                .size(width = 300.dp, height = 150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign Up",
            color = MidGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Create an new account",
            color = LightGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        UnderLinedEditText(
            state = state.name,
            filedName = "Name",
            onValueChange = { onEvent(SignUpEvent.NameChanged(it)) },
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterName
        )

        UnderLinedEditText(
            state = state.email,
            filedName = "Email",
            onValueChange = { onEvent(SignUpEvent.EmailChanged(it)) },
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterEmail
        )

        UnderLinedEditText(
            state = state.phone,
            filedName = "Phone",
            onValueChange = { onEvent(SignUpEvent.PhoneChanged(it)) },
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterPhone,
            keyboardType = KeyboardType.Phone
        )

        UnderLinedEditText(
            state = state.password,
            filedName = "Password",
            onValueChange = { onEvent(SignUpEvent.PasswordChanged(it)) },
            isPasswordField = true,
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterPassword
        )

        UnderLinedEditText(
            state = state.confirmPassword,
            filedName = "Confirm Password",
            onValueChange = { onEvent(SignUpEvent.ConfirmPasswordChanged(it)) },
            isPasswordField = true,
            imeAction = ImeAction.Done,
            focusRequester = focusRequesterConfirmPassword
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Already have an account?",
                color = Primary,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.clickable {

                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            var checkState by remember { mutableStateOf(false) }
            Checkbox(
                checked = checkState,
                onCheckedChange = { checkState = it },
                colors = CheckboxDefaults.colors(checkedColor = Primary)
            )
            Text(
                text = stringResource(R.string.agree_signup),
                color = LightGrey,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp), shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary), onClick = {
                onSubmitClick()
            }) {
            Text(
                text = "Sign up",
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
    LoadingOverlay(isLoading = state.isLoading)

}

@Composable
@Preview(showBackground = true)
fun SignUpScreenContentPreview() {
    SignUpScreenContent(state = SignUpUiState(), onEvent = {}, onSubmitClick = {})
}