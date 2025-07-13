package com.webenia.eleganceoud.presentation.screens.product_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.SwipeImageSlider
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun ProductScreenSetup(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    ProductScreenContent(
        state = viewModel.uiState
    )
}

@Composable
fun ProductScreenContent(
    state: ProductDetailsUiState
) {
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
                fontSize = 14.sp,
                maxLines = 10,
                softWrap = true,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                color = MidGrey,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Composable
@Preview
fun ProductScreenContentPreview() {
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
                amounts = emptyList(),
                relatedProducts = emptyList(),
                imagesList = emptyList(),
                priceAfterDiscount = 90.0,
                currencyCode = "AED"
            )
        )
    )
}