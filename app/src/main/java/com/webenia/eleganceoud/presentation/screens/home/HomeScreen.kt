package com.webenia.eleganceoud.presentation.screens.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.BrandItem
import com.webenia.eleganceoud.presentation.composables.CategoryItem
import com.webenia.eleganceoud.presentation.composables.CategoryItemPreview
import com.webenia.eleganceoud.presentation.composables.LoadingOverlay
import com.webenia.eleganceoud.presentation.composables.ProductItem
import com.webenia.eleganceoud.presentation.composables.TopBar
import com.webenia.eleganceoud.ui.theme.Primary


@Composable
fun HomeScreenSetup(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.getHome()
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvents.ShowToast -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT)
                        .show()
                }

                else -> Unit
            }
        }
    }
    HomeScreenContent(
        state = viewModel.uiState
    )
}

@Composable
fun HomeScreenContent(
    state: HomeUiState,
) {

    val context = LocalContext.current
    if (state.isLoading) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            HomeShimmerEffect()
        }
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxSize()
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cubes),
                    contentDescription = "shopping",
                    tint = Primary,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    "Categories",
                    color = Primary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(state.categoriesList.size) { item ->
                    CategoryItem(
                        item = state.categoriesList[item]
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_diagram),
                    contentDescription = "diagram",
                    tint = Primary,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    "Our Brands",
                    color = Primary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(state.brandsList.size) { item ->
                    BrandItem(
                        item = state.brandsList[item]
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_shopping),
                    contentDescription = "shopping",
                    tint = Primary,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    "Our Products",
                    color = Primary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(
                    state.ourProductsList.size,
                ) { product ->
                    ProductItem(
                        item = state.ourProductsList[product],
                        modifier = Modifier.clickable {

                        }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_shopping),
                    contentDescription = "shopping",
                    tint = Primary,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    "Latest Products",
                    color = Primary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(
                    state.latestProductsList.size,
                ) { product ->
                    ProductItem(
                        item = state.latestProductsList[product],
                        modifier = Modifier.clickable {
                        }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(25.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Primary
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = "shopping",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                    }
                }
                Text(
                    "Best-selling Products",
                    color = Primary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold
                )

            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(
                    state.bestSellingList.size,
                ) { item ->
                    ProductItem(
                        item = state.bestSellingList[item],
                        modifier = Modifier.clickable {
                        }
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    val list = listOf(
        ProductUiModel(
            id = 1,
            name = "Test",
            description = "",
            price = "0.0",
            imageUrl = "",
            currencyCode = "",
            isAvailable = true
        ),
        ProductUiModel(
            id = 1,
            name = "Test",
            description = "",
            price = "0.0",
            imageUrl = "",
            currencyCode = "",
            isAvailable = true
        ),

        )
    HomeScreenContent(
        state = HomeUiState(
            ourProductsList = list,
            isLoading = true
        )
    )
}