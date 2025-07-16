package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.ui.theme.HoverGrey
import com.webenia.eleganceoud.ui.theme.LightGreen
import com.webenia.eleganceoud.ui.theme.Primary
import com.webenia.eleganceoud.ui.theme.VeryLightGrey

@Composable
fun ProductItemWide(
    item: ProductUiModel
) {
    val price = item.price
    val priceAfterDiscount = item.priceAfterDiscount
    val finalPrice = if (item.hasDiscount) priceAfterDiscount else price
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = HoverGrey
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(.4f) // 50%
            ) {
                AsyncImage(
                    model = BASE_IMAGE_URL+item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .weight(.6f) // 50%
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                if (item.hasDiscount) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.Red.copy(alpha = .8f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(
                                    horizontal = 4.dp,
                                )
                        ) {
                            Text(
                                text = buildString {
                                    append(item.discount?.toInt())
                                    append(" ")
                                    append("%")
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = buildString {
                                append(item.price)
                                append(" ")
                                append(item.currencyCode)
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(5.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = LightGreen,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 6.dp,
                            vertical = 2.dp
                        )
                ) {

                    Text(
                        text = buildString {
                            append(
                                finalPrice
                            )
                            append(" ")
                            append(item.currencyCode)
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround){
                    ToggleHeartIcon(
                        modifier = Modifier.size(
                            40.dp
                        )
                    ) {

                    }
                    AddToCartIcon(
                        modifier = Modifier.size(
                            40.dp
                        )
                    ) {

                    }

                }
            }

        }
    }
}

@Composable
@Preview
fun PreviewProductItemWide() {
    ProductItemWide(
        item = ProductUiModel(
            id = 1,
            name = "Test",
            description = "",
            price = 0.0,
            imageUrl = "",
            currencyCode = "",
            isAvailable = false,
            hasDiscount = true
        )
    )

}