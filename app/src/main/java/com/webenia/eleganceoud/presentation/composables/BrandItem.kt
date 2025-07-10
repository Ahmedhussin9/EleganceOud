package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
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
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.brands.BrandUiModel
import com.webenia.eleganceoud.ui.theme.CardGrey

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
            model = BASE_IMAGE_URL + item.logoUrl,
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
    }
}
@Composable
fun BrandItemShimmer() {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),
        colors = CardDefaults.cardColors(
            containerColor = CardGrey
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)

            )
        }
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