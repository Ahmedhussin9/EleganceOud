package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun LoadingOverlay(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)) // semi-transparent
                .zIndex(1f), // ensure it's above other content
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Primary,
                strokeWidth = 4.dp
            )
        }
    }
}
