package com.webenia.eleganceoud.presentation.screens.favorite

sealed class FavoriteEvent {
    object GetFavorites : FavoriteEvent()
    data class FavoriteClick(val productId: Int) : FavoriteEvent()
    data class AddToCartClick(val productId: Int) : FavoriteEvent()
    object OnBackClick : FavoriteEvent()
    data class ProductClicked(val productId: Int) : FavoriteEvent()
}