package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elegance_oud.util.imageUrlExt
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.domain.model.brands.BrandUiModel

@Composable
fun BrandItem(item: BrandUiModel) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        AsyncImage(
            model = imageUrlExt + item.logoUrl,
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
    }
}

@Preview
@Composable
fun PreviewBrandItem() {
    BrandItem(
        item = BrandUiModel(
            id = 1,
            name = "Test",
            logoUrl = ""
        )
    )

}