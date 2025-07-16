package com.webenia.eleganceoud.presentation.screens.category_products

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.BackButton
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.presentation.screens.product_details.ProductDetailsEvent
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun CategoryProductsSetup(
    navController: NavController,
    categoryId: Int,
    viewModel: CategoryProductsViewModel = hiltViewModel()
) {


    CategoryProductsContent(
        state = viewModel.uiState
    )
}

@Composable
fun CategoryProductsContent(
    state: CategoryProductsUiState
) {

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
            ) {
                ProductItemWide(
                    item = state.categoryProducts?.get(it)!!
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategoryProducts() {
    CategoryProductsContent(
        state = CategoryProductsUiState()
    )
}
