package com.webenia.eleganceoud.presentation.composables


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun WeightItem(
    item: ProductAmountUiModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Primary else Color.LightGray,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = "${item.weight} ${item.unit} for ${item.price}",
            color = if (isSelected) Color.White else Color.Black,
            maxLines = 1,
            softWrap = true,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }

}

@Composable
@Preview(showBackground = true)
fun WeightItemPreview() {
    val item = ProductAmountUiModel(
        weight = 100,
        price = "100",
        unit = "kg"
    )
    WeightItem(
        item = item,
        isSelected = true,
        onClick = {}
    )
}