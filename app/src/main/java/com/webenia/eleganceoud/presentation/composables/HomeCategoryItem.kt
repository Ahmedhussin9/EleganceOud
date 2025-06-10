package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.domain.model.CategoryItem
import com.webenia.eleganceoud.ui.theme.HoverGrey
import com.webenia.eleganceoud.ui.theme.LightGrey
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.VeryLightGrey

@Composable
fun HomeCategoryItem(item: CategoryItem) {
    Box(
        modifier = Modifier
            .background(VeryLightGrey)
            .clip(RoundedCornerShape(10.dp))
            .size(width = 100.dp, height = 120.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = item.imageUrl,
                placeholder = painterResource(R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(height = 60.dp, width = 100.dp)
            )
            Row (modifier = Modifier.fillMaxSize().background(HoverGrey),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = item.nameEn,
                    color = Color.Black,
                    modifier = Modifier.padding(5.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )


            }

        }

    }


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeCategoryItemPreview() {
    HomeCategoryItem(
        item = CategoryItem(
            id = 1,
            nameEn = "test",
            nameAr = "test",
            imageUrl = "test"
        )
    )
}