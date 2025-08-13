package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.cart.CartItemModel
import com.webenia.eleganceoud.presentation.ui.theme.HoverGrey
import com.webenia.eleganceoud.presentation.ui.theme.LightGreen
import com.webenia.eleganceoud.presentation.ui.theme.Primary


@Composable
fun CartProductItem(
    modifier: Modifier = Modifier,
    item: CartItemModel,
    onClick: (productId: Int) -> Unit,
    onPlusClick: (productId: Int) -> Unit,
    onMinusClick: (productId: Int) -> Unit,
    onDeleteClick: (productId: Int) -> Unit,
    countState: Int = 1,
) {
    val price = item.price
    val priceAfterDiscount = item.priceAfterDiscount
    val finalPrice = if (item.hasDiscount) priceAfterDiscount else price

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(item.id) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, HoverGrey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = BASE_IMAGE_URL + item.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(10.dp))


            Column(
                modifier = Modifier
                    .weight(0.65f)
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.SemiBold,
                        color = Primary,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onDeleteClick(item.id) }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(LightGreen, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${finalPrice} ${item.currencyCode}",
                            fontSize = 16.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    QuantityCounter(
                        count = countState,
                        onMinus = { onMinusClick(item.id) },
                        onPlusClick = { onPlusClick(item.id) },
                        fontSize = 14,
                        iconModifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewCartProductItem() {
    CartProductItem(
        item = CartItemModel(
            id = 1,
            name = "Test",
            description = "",
            price = 0.0,
            imageUrl = "",
            currencyCode = "",
            hasDiscount = true,
            cartId = 1
        ),
        onClick = {},
        onPlusClick = {},
        onMinusClick = {},
        onDeleteClick = {},
    )

}