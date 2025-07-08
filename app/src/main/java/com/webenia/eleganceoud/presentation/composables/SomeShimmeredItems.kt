package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webenia.eleganceoud.ui.theme.CardGrey

@Composable
fun ShimmerIconWithText() {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon shimmer
        ShimmerEffect(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(
                  CardGrey
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Text shimmer
        ShimmerEffect(
            modifier = Modifier.
                height(30.dp).width(160.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    CardGrey
                )
        )
    }
}
@Preview
@Composable
fun PreviewThis(){
    ShimmerIconWithText()
}
