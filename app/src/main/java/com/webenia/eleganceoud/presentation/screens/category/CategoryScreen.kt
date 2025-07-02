package com.webenia.eleganceoud.presentation.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CategoryScreenSetup() {
    CategoryScreenContent()
}

@Composable
fun CategoryScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Category Screen",
            fontSize = 20.sp
        )

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewCategoryScreen() {
    CategoryScreenContent()
}