package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elegance_oud.util.imageUrlExt
import com.webenia.eleganceoud.domain.model.category.CategoryUiModel
import com.webenia.eleganceoud.ui.theme.CardGrey

@Composable
fun CategoryItem(
    item: CategoryUiModel
) {
    Card(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    size = 10.dp
                )
            )
            .height(160.dp)
            .width(140.dp), colors = CardDefaults.cardColors(
            containerColor = CardGrey
        ), elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(5.dp)).fillMaxSize(),
                    model = imageUrlExt+item.imageUrl,
                    contentDescription = item.name
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }


        }

    }
}

@Preview()
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        item = CategoryUiModel(
            id = 1,
            name = "Test",
            imageUrl = ""
        )
    )
}