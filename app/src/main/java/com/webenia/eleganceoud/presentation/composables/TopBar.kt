package com.webenia.eleganceoud.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun TopBar(
    onSearchClick: () -> Unit,
    onBurgerClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (listRef, logoRef, searchRef) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_burger),
                contentDescription = "Back",
                tint = Primary,
                modifier = Modifier
                    .constrainAs(listRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(35.dp)
                    .clickable {
                        onBurgerClick()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.plain_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .constrainAs(logoRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .size(50.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Primary,
                modifier = Modifier
                    .size(35.dp)
                    .constrainAs(searchRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        onSearchClick()
                    }
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewTopBar() {
    TopBar(
        onSearchClick = {},
        onBurgerClick = {}

    )
}