package com.webenia.eleganceoud.presentation.screens.signin

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.UnderLinedEditText
import com.webenia.eleganceoud.presentation.screens.otp.navigateToOTPScreen
import com.webenia.eleganceoud.presentation.screens.signup.SignUpEvent
import com.webenia.eleganceoud.ui.theme.LightGrey
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary
import com.webenia.eleganceoud.ui.theme.poppinsFamily



@Composable
fun SignInScreenSetup(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    SignInScreenContent(state = viewModel.uiState, onEvent = viewModel::onEvent,
        onSignInClick = { navController.navigateToOTPScreen() })
}

@Composable
fun SignInScreenContent(
    state: SignInUiState = SignInUiState(),
    onEvent: (SignInEvent) -> Unit,
    onSignInClick: () -> Unit,

) {
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        BackButton {

        }
        Image(
            painter = painterResource(R.drawable.img_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .align(CenterHorizontally)
                .size(width = 300.dp, height = 150.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Welcome!",
            color = MidGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "login or sign up to continue in our app",
            color = LightGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        UnderLinedEditText(
            state = state.email,
            filedName = "Email",
            onValueChange = { onEvent(SignInEvent.EmailChanged(it)) },
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterEmail
        )
        UnderLinedEditText(
            state = state.password,
            filedName = "Password",
            onValueChange = { onEvent(SignInEvent.PasswordChanged(it)) },
            isPasswordField = true,
            imeAction = ImeAction.Next,
            focusRequester = focusRequesterPassword
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Don't have an account yet?",
                color = Primary,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.clickable {

                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp), shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary), onClick = {
                onSignInClick()
            }) {
            Text(
                text = "Sign in",
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenContentPreview() {
    SignInScreenContent(
        state = SignInUiState(),
        onEvent = {},
        onSignInClick = {}
    )
}