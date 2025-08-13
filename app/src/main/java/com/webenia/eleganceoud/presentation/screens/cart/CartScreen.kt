package com.webenia.eleganceoud.presentation.screens.cart


import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.presentation.composables.CartProductItem
import com.webenia.eleganceoud.presentation.composables.ProductItemWide
import com.webenia.eleganceoud.presentation.composables.ReloadButton
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.favorite.FavoriteEvent
import com.webenia.eleganceoud.presentation.screens.favorite.FavoriteShimmer
import com.webenia.eleganceoud.presentation.ui.theme.Primary
import com.webenia.eleganceoud.util.state.UiText

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
            }
        }
    }
    CartScreenContent(
        state = viewModel.uiState,
        onEvent = viewModel::onEvent
    )

}

@Composable
fun CartScreenContent(
    state: CartUiState,
    onEvent: (CartEvents) -> Unit
) {
    if(state.isLoading){
        FavoriteShimmer()
    }else if(state.error!=null){
        ReloadButton(
            onClick = {
                onEvent(CartEvents.OnReloadClick)
            }
        )
    }else{
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp)
            ) {
                Text(
                    text = stringResource(R.string.cart),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )

                val items = state.cartModel.cartItems.orEmpty()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items) { item ->
                        CartProductItem(
                            item = item,
                            countState = item.countInCart,
                            onClick = {
                                onEvent(CartEvents.OnProductClick(item))
                            },
                            onPlusClick = {
                                onEvent(CartEvents.OnPlusClick(item))
                            },
                            onMinusClick = {
                                onEvent(CartEvents.OnMinusClick(item))
                            },
                            onDeleteClick = {
                                onEvent(CartEvents.OnDeleteClick(item))
                            },

                            )
                    }
                }
            }

            Button(
                onClick = {
                    onEvent(CartEvents.OnCheckoutClicked)
                },
                modifier = Modifier
                    .height(50.dp)
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.checkout),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(
                        modifier = Modifier.widthIn(10.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

            }
        }
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewCartScreen() {
    CartScreenContent(
        state = CartUiState(
           
        ),
        onEvent = {}

    )
}