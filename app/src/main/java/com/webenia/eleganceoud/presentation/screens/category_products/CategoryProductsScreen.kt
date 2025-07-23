package com.webenia.eleganceoud.presentation.screens.category_products

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.presentation.composables.ShimmerEffect
import com.webenia.eleganceoud.presentation.composables.shimmerEffect
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.product_details.ProductDetailsEvent
import com.webenia.eleganceoud.ui.theme.CardGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun CategoryProductsSetup(
    navController: NavController,
    categoryId: Int,
    viewModel: CategoryProductsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.uiEvent.collect {
            when (it) {
                is CategoryProductUiEvents.Navigate -> {
                    when (val destination = it.destination) {
                        is AppDestination.ProductDetails -> {
                            navController.navigate(
                                destination.createRoute(destination.productId)
                            )
                        }

                        else -> {
                            navController.navigate(it.destination.route)
                        }
                    }

                }

                is CategoryProductUiEvents.ShowToast -> {
                    Toast.makeText(context, it.message.asString(context), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    LaunchedEffect(true) {
        viewModel.getCategoryProducts(
            categoryId = categoryId,
            currency = "AED"
        )
    }
    CategoryProductsContent(
        state = viewModel.uiState,
        onBackClick = onBackClick,
        events = {
            viewModel.onEvent(it)
        }
    )
}

@Composable
fun CategoryProductsContent(
    state: CategoryProductsUiState,
    onBackClick: () -> Unit,
    events: (CategoryProductEvents) -> Unit
) {
    if (
        state.isLoading
    ) {
        CategoryProductsShimmer()
    } else {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val backButton = createRef()
                val title = createRef()
                BackButton(modifier = Modifier
                    .size(40.dp)
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }) {
                    onBackClick.invoke()
                }
                Text(
                    text = state.categoryName ?: "Category Name",
                    fontSize = 24.sp,
                    maxLines = 1,
                    softWrap = true,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    color = Primary,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    state.categoryProducts?.size ?: 0
                ) { index ->
                    Log.e("Fav state", state.categoryProducts?.get(index)!!.isFavorite.toString(), )
                    ProductItemWide(
                        item = state.categoryProducts?.get(index)!!,
                        onClick = {
                            events.invoke(CategoryProductEvents.OnCategoryProductClicked(state.categoryProducts[index]))
                            Log.e("index", index.toString(), )
                        },
                        onFavClick = {

                            Log.e("index", it.toString(), )
                            events.invoke(CategoryProductEvents.OnFavClicked(state.categoryProducts[index]))

                        },
                        onAddToCartClick = {
                            events.invoke(CategoryProductEvents.OnAddToCartClicked(state.categoryProducts[index]))
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun CategoryProductsShimmer() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Top Bar Shimmer (Back button + Title)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardGrey)
            )

            ShimmerEffect(
                modifier = Modifier
                    .width(180.dp)
                    .height(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(CardGrey)
            )

            // Optional extra icon space
            Spacer(modifier = Modifier.width(40.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

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
fun ProductItemShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CardGrey)
            .padding(8.dp)
    ) {
        ShimmerEffect(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CardGrey)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.fillMaxSize()
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CardGrey)
            )
            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CardGrey)
            )
            ShimmerEffect(
                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CardGrey)
            )
        }
    }
}


@Preview
@Composable
fun PreviewCategoryProducts() {
    CategoryProductsContent(
        state = CategoryProductsUiState(
            isLoading = false
        ),
        onBackClick = {},
        events = {}
    )
}
