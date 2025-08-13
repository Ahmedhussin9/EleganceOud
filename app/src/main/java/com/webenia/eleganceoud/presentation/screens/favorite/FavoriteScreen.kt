package com.webenia.eleganceoud.presentation.screens.favorite

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.presentation.composables.ReloadButton
import com.webenia.eleganceoud.presentation.screens.category_products.ProductItemShimmer
import com.webenia.eleganceoud.presentation.ui.theme.Primary

@Composable
fun FavoriteScreenSetup(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getFavorites()
    }
    LaunchedEffect(
        true
    ) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is FavoriteUiEvents.Navigate -> {
                    navController.navigate(event.destination.route)
                }

                is FavoriteUiEvents.ShowToast -> {
                    Toast.makeText(
                        context, event.message.asString(
                            context
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    FavoriteScreenContent(
        viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FavoriteScreenContent(
    state: FavoriteUiState,
    onEvent: (FavoriteEvent) -> Unit
) {
    if (state.isLoading) {
        FavoriteShimmer()
    } else if (state.error != null) {
        ReloadButton(
            {
                onEvent(FavoriteEvent.OnReloadClicked)
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.favorites),
                    modifier = Modifier.padding(10.dp),
                    color = Primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }
            Spacer(
                modifier = Modifier.padding(10.dp)
            )
            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                state.products?.let {
                    items(it.size) { item ->
                        state.products[item]?.let { product ->
                            ProductItemWide(
                                item = product,
                                onClick = {
                                },
                                onFavClick = {
                                    onEvent(FavoriteEvent.FavoriteClick(it))
                                },
                                onAddToCartClick = {
                                    onEvent(FavoriteEvent.AddToCartClick(it))
                                }
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun FavoriteShimmer() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // List of wide items shimmer
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(6) {
                ProductItemShimmer()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewFavoriteScreen() {
    FavoriteScreenContent(
        state = FavoriteUiState(
            isLoading = true
        ),
        onEvent = {}

    )
}