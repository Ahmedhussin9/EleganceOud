package com.webenia.eleganceoud.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CustomRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        // Return your preferred ripple color globally
        return Primary.copy(alpha = 0.3f)
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            lightTheme = !isSystemInDarkTheme()
        )
}