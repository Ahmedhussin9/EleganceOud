package com.webenia.eleganceoud.domain.model.brands

import com.webenia.eleganceoud.data.remote.response.home.brands.Brand

data class BrandUiModel(
    val id: Int,
    val name: String,
    val logoUrl: String
)

fun Brand.toUiModel(): BrandUiModel {
    return BrandUiModel(
        id = id,
        name = name_en,
        logoUrl = images.firstOrNull()?.path.orEmpty()
    )
}
