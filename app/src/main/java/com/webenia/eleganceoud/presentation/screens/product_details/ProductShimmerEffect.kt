package com.webenia.eleganceoud.presentation.screens.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webenia.eleganceoud.presentation.composables.ProductItemShimmer
import com.webenia.eleganceoud.presentation.composables.ShimmerEffect
import com.webenia.eleganceoud.ui.theme.CardGrey

@Composable
fun ProductDetailsShimmer() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProductTopBarShimmer()
        // Image placeholder
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    CardGrey
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Text lines
        repeat(5) {
            Spacer(modifier = Modifier.height(8.dp))
            ShimmerEffect(
                modifier = Modifier
                    .background(
                        CardGrey
                    )
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Price row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .background(
                        CardGrey
                    )
                    .width(100.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            ShimmerEffect(
                modifier = Modifier
                    .background(
                        CardGrey
                    )
                    .width(60.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .width(120.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CardGrey)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(CardGrey)
                )

                Spacer(modifier = Modifier.width(8.dp))

                ShimmerEffect(
                    modifier = Modifier
                        .width(50.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(CardGrey)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Related products
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(5) {
                ProductItemShimmer()
            }
        }
    }
}

@Composable
fun ProductTopBarShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Back icon placeholder
        ShimmerEffect(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(CardGrey)
        )

        // Title placeholder
        ShimmerEffect(
            modifier = Modifier
                .height(36.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(CardGrey)
        )

        // Cart icon or other icon placeholder
        ShimmerEffect(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(CardGrey)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsShimmerPreview() {
    ProductDetailsShimmer()
}