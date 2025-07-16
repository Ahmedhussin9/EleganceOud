package com.webenia.eleganceoud.presentation.screens.category


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.presentation.composables.CategoryItem
import com.webenia.eleganceoud.presentation.composables.CategoryItemShimmer
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.home.HomeEvents
import com.webenia.eleganceoud.ui.theme.Primary


@Composable
fun CategoryScreenSetup(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CategoryUiEvents.Navigate -> {
                    when (val destination = event.destination) {
                        is AppDestination.CategoryProduct ->
                            navController.navigate(destination.createRoute(destination.categoryId)
                            )
                        else -> navController.navigate(destination.route)
                    }

                }

                is CategoryUiEvents.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message.asString(context), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    CategoryScreenContent(
        state = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CategoryScreenContent(
    state: CategoryUiState,
    onEvent: (CategoryEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        if (state.isLoading) {
            CategoryShimmerEffect()
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Categories",
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
                items(state.categoriesList.size) { item ->
                    CategoryItem(
                        item = state.categoriesList[item]
                        , onClick = {
                            onEvent(CategoryEvent.OnCategoryClicked(state.categoriesList[item]))

                        }
                    )
                }
            }
        }
    }

}

@Composable
fun CategoryShimmerEffect() {
    LazyVerticalGrid(
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(6) { // show 6 shimmer items
            CategoryItemShimmer()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewCategoryScreen() {
    CategoryScreenContent(
        state = CategoryUiState(),
        onEvent = { }
    )
}