package com.webenia.eleganceoud.presentation.composables


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary
import com.webenia.eleganceoud.ui.theme.poppinsFamily

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
    focusRequester: FocusRequester = FocusRequester.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = filedName,
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
        )
    }
    TextField(
        value = state,
        onValueChange = { onValueChange(it) },
        singleLine = isSingleLine,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        textStyle = TextStyle( // 👇 smaller text
            fontSize = 14.sp,
            lineHeight = 16.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Primary,
            unfocusedTextColor = Primary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor =Color.Transparent,
            focusedIndicatorColor = Primary,
            unfocusedIndicatorColor = MidGrey,
            ),
        modifier = modifier
            .fillMaxWidth()
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