package com.webenia.eleganceoud.presentation.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun FavoriteScreenSetup(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
) {
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
    Column(
        modifier = Modifier
            .padding(
                10.dp
            )
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Favorites",
                modifier = Modifier.padding(10.dp),
                color = Primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(10.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            state.products?.let {
                items(it.size) { item ->
                    state.products[item]?.let { product ->
                        ProductItemWide(
                            item = product, onClick = {
                            }
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewFavoriteScreen() {
    FavoriteScreenContent(
        state = FavoriteUiState(
            isLoading = false
        ),
        onEvent = {}

    )
}