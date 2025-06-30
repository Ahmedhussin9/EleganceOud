package com.webenia.eleganceoud

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elegance_oud.util.UserUtil
import com.webenia.eleganceoud.presentation.navigation.NavGraph
import com.webenia.eleganceoud.presentation.screens.otp.OtpScreenSetup
import com.webenia.eleganceoud.presentation.screens.signin.SignInScreenSetup
import com.webenia.eleganceoud.presentation.screens.signup.SignUpScreenSetup
import com.webenia.eleganceoud.ui.theme.EleganceOudTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        UserUtil.init(context)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            EleganceOudTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(paddingValues)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        NavGraph(navController)
                    }
                }
            }
        }
    }
}
