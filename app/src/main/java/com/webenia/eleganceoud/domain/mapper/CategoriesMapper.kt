package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.home.category.Category
import com.webenia.eleganceoud.domain.model.category.CategoryUiModel

fun Category.toUiModel(): CategoryUiModel {
    return CategoryUiModel(
        id = id,
        name = name,
        imageUrl = images.firstOrNull()?.path.orEmpty()
    )
}