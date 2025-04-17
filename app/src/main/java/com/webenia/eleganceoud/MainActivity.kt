package com.webenia.eleganceoud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.webenia.eleganceoud.presentation.composables.UnderLinedEditText
import com.webenia.eleganceoud.presentation.screens.otp.OtpScreenSetup
import com.webenia.eleganceoud.presentation.screens.signin.SignInScreenSetup
import com.webenia.eleganceoud.presentation.screens.signup.SignUpScreenSetup
import com.webenia.eleganceoud.ui.theme.EleganceOudTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EleganceOudTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){ paddingValues ->
                    Column(
                        modifier = Modifier.background(Color.White).padding(paddingValues).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        OtpScreenSetup()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EleganceOudTheme {

    }
}