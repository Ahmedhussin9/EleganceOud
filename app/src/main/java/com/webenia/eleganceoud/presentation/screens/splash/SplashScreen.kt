package com.webenia.eleganceoud.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webenia.eleganceoud.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenSetup(
    onDone: () -> Unit = {}
) {
    SplashScreenContent(onDone)
}

@Composable
fun SplashScreenContent(
    onDone: () -> Unit = {}
) {
    LaunchedEffect(true) {
        delay(1000)
        onDone()
    }
    Column(
        modifier = Modifier.background(Color.White).fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .background(Color.White)
                .size(200.dp)
        )
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreenPreview() {
    SplashScreenContent()
}


