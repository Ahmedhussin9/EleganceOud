package com.webenia.eleganceoud.presentation.screens.cart


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.presentation.screens.favorite.FavoriteEvent
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun CartScreenSetup(
    viewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.getCart()
    }
    LaunchedEffect(true) {
        viewModel.uiEvent.collect {
            when (it) {
                is CartUiEvents.ShowToast -> {
                    Toast.makeText(context, it.message.asString(context), Toast.LENGTH_SHORT).show()
                }

                is CartUiEvents.Navigate -> {
                    navController.navigate(it.destination.route)
                }
            }
        }
    }
    CartScreenContent(
        state = viewModel.uiState
    )

}

@Composable
fun CartScreenContent(
    state: CartUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.cart),
                modifier = Modifier.padding(10.dp),
                color = Primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        val items = state.cartModel.cartItems.orEmpty()

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(items) { item ->
                ProductItemWide(
                    item = item,
                    onClick = { /* handle click */ },
                    onFavClick = { /* handle fav */ },
                    onAddToCartClick = { /* handle add */ }
                )
            }
        }

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewCartScreen() {
    CartScreenContent(
        state = CartUiState()
    )
}