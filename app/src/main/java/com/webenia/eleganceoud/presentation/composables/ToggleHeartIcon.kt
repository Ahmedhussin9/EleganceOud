package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.ui.theme.VeryLightGrey

@Composable
fun ToggleHeartIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Icon(
        painter = if (isFavorite) painterResource(
            id = R.drawable.ic_favorite
        ) else painterResource(
            id = R.drawable.ic_fav_empty
        ),
        contentDescription = "Favorite",
        tint = if (isFavorite) Color.Red else Color.Black,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .size(40.dp)
            .background(color = VeryLightGrey)
            .clickable {
                isFavorite = !isFavorite
                onClick()
            }
            .padding(10.dp)
    )
}
