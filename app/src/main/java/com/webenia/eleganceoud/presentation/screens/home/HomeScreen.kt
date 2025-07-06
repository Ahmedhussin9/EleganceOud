package com.webenia.eleganceoud.presentation.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
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
    Column(modifier = Modifier.fillMaxSize())
    {
        Log.e("state", state.toString(), )
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
                    item = state.ourProductsList[product]
                )
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
            ourProductsList = list
        )
    )
}