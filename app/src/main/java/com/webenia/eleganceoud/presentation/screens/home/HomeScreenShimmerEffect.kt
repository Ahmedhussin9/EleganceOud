package com.webenia.eleganceoud.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webenia.eleganceoud.presentation.composables.BrandItemShimmer
import com.webenia.eleganceoud.presentation.composables.CategoryItemShimmer
import com.webenia.eleganceoud.presentation.composables.ProductItemShimmer
import com.webenia.eleganceoud.presentation.composables.ShimmerEffect
import com.webenia.eleganceoud.presentation.composables.ShimmerIconWithText
import com.webenia.eleganceoud.ui.theme.CardGrey

@Composable
fun HomeShimmerEffect() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShimmerIconWithText()
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                CategoryItemShimmer()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShimmerIconWithText()
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Transparent),
//            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                BrandItemShimmer()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShimmerIconWithText()
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                ProductItemShimmer()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShimmerIconWithText()
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                ProductItemShimmer()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}


@Preview
@Composable
fun PreviewHomeShimmerEffect() {
    HomeShimmerEffect()

}