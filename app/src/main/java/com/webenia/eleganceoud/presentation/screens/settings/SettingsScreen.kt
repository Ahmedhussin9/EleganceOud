package com.webenia.eleganceoud.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.ui.theme.CardGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun SettingsScreenSetup(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.uiEvent.collect {
            when (it) {
                is SettingsUiEvents.Navigate -> {
                    navController.navigate(it.destination.route) {
                        if (it.destination.route == AppDestination.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }

                is SettingsUiEvents.ShowToast -> {
                    Toast.makeText(context, it.message.asString(context), Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
    SettingsScreenContent(
        onBackClick = {
            navController.popBackStack()
        },
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@Composable
fun SettingsScreenContent(
    onBackClick: () -> Unit,
    onEvent: (SettingsEvent) -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            BackButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(20.dp))
                    .size(40.dp)
            ) {
                onBackClick()
            }

            Text(
                text = "Settings",
                color = Primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()

        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardGrey
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .clickable {
                        showLogoutDialog = true
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Log out",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }


        }

    }
    LogoutConfirmationDialog(
        showDialog = showLogoutDialog,
        onConfirm = {
            onEvent(SettingsEvent.SignOut)
            showLogoutDialog = false
        },
        onDismiss = { showLogoutDialog = false }
    )
}

@Composable
fun LogoutConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Confirm Logout")
            },
            text = {
                Text(text = "Are you sure you want to log out?")
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(text = "Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

@Composable
@Preview
fun PreviewSettingsScreen() {
    SettingsScreenContent(
        onBackClick = {},
        onEvent = {}
    )
}