package com.webenia.eleganceoud.presentation.screens.product_details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.AddToCartRow
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.ExpandableText
import com.webenia.eleganceoud.presentation.composables.ProductItem
import com.webenia.eleganceoud.presentation.composables.SwipeImageSlider
import com.webenia.eleganceoud.presentation.composables.WeightItem
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun ProductScreenSetup(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    ProductScreenContent(
        state = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ProductScreenContent(
    state: ProductDetailsUiState,
    onEvent: (ProductDetailsEvent) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { ProductTopBar(state = state) },
        bottomBar = {
            AddToCartRow(
                onAddToCart = {
                    // Your add to cart logic
                }
            )
        },
        modifier = Modifier.background(Color.White)
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                SwipeImageSlider(images = state.productDetails?.imagesList ?: emptyList())
            }

            item {
                ExpandableText(
                    text = state.productDetails?.description ?: "",
                    textColor = MidGrey,
                    fontSize = 16.sp
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PRICE",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Text(
                            text = buildString {
                                append(state.productDetails?.price.toString())
                                append(" ")
                                append("${state.productDetails?.currencyCode}")
                            },
                            fontSize = 18.sp,
                            color = Primary,
                            fontWeight = FontWeight.Bold,
                        )
                        if (state.productDetails?.discount != null) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = buildString {
                                    append(state.productDetails.priceAfterDiscount.toString())
                                    append(" ")
                                    append(state.productDetails.currencyCode)
                                },
                                fontSize = 18.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(textDecoration = LineThrough)
                            )
                        }
                    }
                }
            }

            if (state.productDetails?.amounts?.isNotEmpty() == true) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = "SELECT WEIGHT",
                            fontSize = 18.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = { selectedIndex = 0 },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Replay,
                                contentDescription = "Reset",
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Reset",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp), // Give it a bounded height
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(state.productDetails.amounts.size) { index ->
                                WeightItem(
                                    item = state.productDetails.amounts[index],
                                    isSelected = selectedIndex == index,
                                    onClick = {
                                        selectedIndex = index
                                        onEvent(
                                            ProductDetailsEvent.OnWeightSelected(
                                                state.productDetails.amounts[index]
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    )
                }
            }

            if (state.productDetails?.relatedProducts?.isNotEmpty() == true) {
                item {
                    Text(
                        text = "RELATED PRODUCTS",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.productDetails.relatedProducts.size) { index ->
                            ProductItem(item = state.productDetails.relatedProducts[index])
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductTopBar(
    state: ProductDetailsUiState
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth().padding(10.dp)
    ) {
        val backButton = createRef()
        val title = createRef()
        val favorite = createRef()
        BackButton(modifier = Modifier
            .size(40.dp)
            .constrainAs(backButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {

        }
        Text(
            text = state.productDetails?.name ?: "",
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
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = Color.Gray,
            modifier = Modifier
                .size(40.dp)
                .constrainAs(favorite) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

    }
}

@Composable
@Preview
fun ProductScreenContentPreview() {
    val amounts = listOf(
        ProductAmountUiModel(
            weight = 1,
            price = "100",
            priceAfter = "90",
            unit = "KG"

        ),
        ProductAmountUiModel(
            weight = 2,
            price = "200",
            priceAfter = "190",
            unit = "KG"
        ),
        ProductAmountUiModel(
            weight = 3,
            price = "300",
            priceAfter = "290",
            unit = "KG"
        ),
        ProductAmountUiModel(
            weight = 4,
            price = "400",
            priceAfter = "390",
            unit = "KG"
        )
    )
    val relatedProducts =
        listOf(
            ProductUiModel(
                id = 1,
                name = "Product Name",
                description = "Product Description",
                price = 100.0,
                isAvailable = true,
                discount = 10.0,
                priceAfterDiscount = 90.0,
                currencyCode = "AED",
                imageUrl = ""
            ),
            ProductUiModel(
                id = 1,
                name = "Product Name",
                description = "Product Description",
                price = 100.0,
                isAvailable = true,
                discount = 10.0,
                priceAfterDiscount = 90.0,
                currencyCode = "AED",
                imageUrl = ""
            )
        )
    ProductScreenContent(
        state = ProductDetailsUiState(
            productDetails = ProductDetailsUiModel(
                id = 1,
                name = "Product Name",
                description = "Product Description Product Description Product Description Product Description Product Description Product Description ",
                price = 100.0,
                isAvailable = true,
                mainImageUrl = "",
                discount = "10",
                amounts = amounts,
                relatedProducts = relatedProducts,
                imagesList = emptyList(),
                priceAfterDiscount = 90.0,
                currencyCode = "AED",
                hasAmount = true
            ),
        ),
        onEvent = {}
    )
}