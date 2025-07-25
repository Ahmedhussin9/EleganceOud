package com.webenia.eleganceoud.presentation.composables

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.webenia.eleganceoud.ui.theme.CardGrey
import com.webenia.eleganceoud.ui.theme.LightGreen

@Composable
fun ProductItem(
    item: ProductUiModel,
    modifier: Modifier = Modifier
) {
    val price = item.price
    val priceAfterDiscount = item.priceAfterDiscount
    val finalPrice = if (item.hasDiscount) priceAfterDiscount else price
    Box(
        modifier = modifier
            .width(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .size(80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                AsyncImage(
                    model = BASE_IMAGE_URL + item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .size(80.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
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
                                    fontSize = 10.sp,
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
                                fontSize = 8.sp,
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
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

            }
        }
        if (!item.isAvailable) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Red.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Unavailable",
                    modifier = Modifier.padding(
                        horizontal = 2.dp,
                    ),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

    }
}

@Composable
fun ProductItemShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(160.dp)
            .width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .size(80.dp)
                    .background(
                        CardGrey
                    )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        CardGrey
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview() {
    ProductItem(
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
//    ProductItemShimmer()
}