package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.ui.theme.Primary

@Composable
fun ReloadButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick, modifier = modifier
                .clip(
                    RoundedCornerShape(
                        20.dp
                    )
                )
                .width(150.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Primary
            )
        ) {
            Text(
                text = stringResource(R.string.reload),
                modifier = Modifier,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ReloadButtonPreview() {
    ReloadButton({

    })
}