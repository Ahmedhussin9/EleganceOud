package com.webenia.eleganceoud.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreenSetup(){
    FavoriteScreenContent()
}
@Composable
fun FavoriteScreenContent(){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Favorite Screen",

        )
    }

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewFavoriteScreen(){
    FavoriteScreenContent()
}