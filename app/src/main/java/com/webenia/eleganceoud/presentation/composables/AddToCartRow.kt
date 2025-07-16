package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun AddToCartRow(
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    var quantity by remember { mutableStateOf(1) }

    Row(
        modifier = modifier
            .background(White)
            .fillMaxWidth()
            .height(
                70.dp
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuantityCounter(
            count = quantity,
            onCountChange = { newCount ->
                quantity = newCount
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                onAddToCart()
            },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = White
            )
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_cart
                ),
                modifier = Modifier.size(20.dp),
                contentDescription = "Add to cart",
                tint = White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "ADD TO CART",
                color = White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                ),
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Composable
fun QuantityCounter(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxHeight()
            .clip(
                RoundedCornerShape(
                    30.dp
                )
            )
            .background(
                LightGray
            )
    ) {
        IconButton(
            onClick = {
                if (count > 1) onCountChange(count - 1)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Minus"
            )
        }

        Text(
            text = count.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = {
                onCountChange(count + 1)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Plus"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddToCartRowPreview() {
    AddToCartRow(
        onAddToCart = {}
    )
}