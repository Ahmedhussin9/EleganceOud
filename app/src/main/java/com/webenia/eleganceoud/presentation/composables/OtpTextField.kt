package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun OtpTextField(
    state: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(state) {
        if (state.length == 5) {
            focusManager.clearFocus() // Hide cursor and remove keyboard focus
        }
    }
    BasicTextField(
        value = state,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        modifier = modifier,
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.height(60.dp)
            ) {
                repeat(5) { index ->
                    val char = when {
                        index >= state.length -> ""
                        else -> state[index].toString()
                    }
                    val isFocused = state.length == index

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(40.dp)
                            .fillMaxHeight()
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Primary else MidGrey,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Text(
                            text = char,
                            style = TextStyle(
                                color = MidGrey,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

        }

    )

}


