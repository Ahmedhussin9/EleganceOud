package com.webenia.eleganceoud

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.elegance_oud.util.LocalUtil
import com.elegance_oud.util.UserUtil
import com.webenia.eleganceoud.presentation.navigation.NavGraph
import com.webenia.eleganceoud.presentation.ui.theme.EleganceOudTheme
import dagger.hilt.android.AndroidEntryPoint


import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import com.webenia.eleganceoud.worker.NotificationScheduler
import com.webenia.eleganceoud.worker.NotifyChannels
import java.time.LocalTime
import java.util.Locale


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        LocalUtil.init(context)
        LocalUtil.loadLocal(this)
        UserUtil.init(context)
        enableDailyReminders(context)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            EleganceOudTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val currentLanguage = LocalUtil.getLang()

                    Log.e("LANG", currentLanguage.toString(), )
                    val layoutDirection = if (currentLanguage == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr


                    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(paddingValues)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            NavGraph(navController)
                        }
                    }
                }
            }
        }
    }
    private fun enableDailyReminders(context: Context) {
        NotifyChannels.ensure(context) // make sure channel exists

        NotificationScheduler.scheduleDailyTimes(
            context = context,
            times = listOf(LocalTime.of(9, 0), LocalTime.of(18, 0)),
            title = "Daily Reminder",
            message = "Quick check-in!"
        )
    }
}
