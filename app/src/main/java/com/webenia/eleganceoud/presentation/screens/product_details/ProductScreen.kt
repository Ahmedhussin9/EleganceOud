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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.webenia.eleganceoud.presentation.composables.BackButton
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



    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(15.dp)
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
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
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            SwipeImageSlider(
                images = state.productDetails?.imagesList ?: emptyList(),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Text(
                text = state.productDetails?.description ?: "",
                fontSize = 16.sp,
                maxLines = 10,
                softWrap = true,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                color = MidGrey,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
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
                        Spacer(
                            modifier = Modifier.width(
                                5.dp
                            )
                        )
                        Text(
                            text = buildString {
                                append(state.productDetails.priceAfterDiscount.toString())
                                append(" ")
                                append(state.productDetails.currencyCode)
                            },
                            fontSize = 18.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                textDecoration = LineThrough
                            )
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            if (state.productDetails?.amounts?.isNotEmpty() == true) {
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
                        onClick = {
                            selectedIndex = 0
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black,
                        )
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Replay,
                            contentDescription = "Favorite",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        state.productDetails.amounts.size
                    ) { index ->
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
            }
        }
    }

}

@Composable
@Preview
fun ProductScreenContentPreview() {
    val amounts = listOf(
        ProductAmountUiModel(
            weight = 1,
            price = "100",
            unit = "KG"
        ),
        ProductAmountUiModel(
            weight = 2,
            price = "200",
            unit = "KG"
        ),
        ProductAmountUiModel(
            weight = 3,
            price = "300",
            unit = "KG"
        ),
        ProductAmountUiModel(
            weight = 4,
            price = "400",
            unit = "KG"
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
                relatedProducts = emptyList(),
                imagesList = emptyList(),
                priceAfterDiscount = 90.0,
                currencyCode = "AED",
                hasAmount = true
            ),
        ),
        onEvent = {}
    )
}