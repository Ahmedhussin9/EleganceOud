package com.webenia.eleganceoud.presentation.screens.otp

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.OtpTextField
import com.webenia.eleganceoud.ui.theme.LightGrey
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary
import com.webenia.eleganceoud.ui.theme.poppinsFamily

@Composable
fun OtpScreenSetup(
    viewModel: OtpScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val timer by viewModel.timerSeconds.collectAsState()
    OtpScreenContent(state = viewModel.uiState, onEvent = viewModel::onEvent, timer = timer)
}

@Composable
fun OtpScreenContent(
    state: OtpUiState = OtpUiState(),
    timer: Int = 60,
    onEvent: (OtpEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Email OTP Verification",
            color = MidGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "GET YOUR CODE",
            color = MidGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Please enter the code sent to you on your email address !",
            color = LightGrey,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        OtpTextField(
            state = state.otp,
            onValueChange = {
                if (it.length <= 5) {
                    onEvent(OtpEvents.DigitEntered(it))
                }
            },
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't receive the code?",
                color = MidGrey,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            if (timer > 0) {
                Text(
                    text = " Resend in ${timer}s",
                    color = LightGrey,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            } else {
                Text(
                    text = " Resend",
                    color = Primary,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onEvent(OtpEvents.ResendCode)
                        }
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp), shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary), onClick = {}) {
            Text(
                text = "Submit",
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OtpScreenPreview() {
    OtpScreenContent(
        state = OtpUiState(),
        onEvent = {}
    )

}