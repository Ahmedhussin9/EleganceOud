package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lint.kotlin.metadata.Visibility

@Composable
fun UnderLinedEditText(
    modifier: Modifier = Modifier,
    state: String = "",
    filedName: String,
    isSingleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    isPasswordField: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = FocusRequester.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }


    OutlinedTextField(
        value = state,
        onValueChange = { onValueChange(it) },
        label = { Text(text = filedName) },
        singleLine = isSingleLine,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        modifier = modifier
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (isPasswordField && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            if (isPasswordField) {
                val icon =
                    if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        }
    )

}

@Composable
@Preview(showBackground = true)
fun UnderLinedEditTextPreview() {
    UnderLinedEditText(filedName = "Name",
        onValueChange = {})
}