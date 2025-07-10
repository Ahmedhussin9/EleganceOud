package com.webenia.eleganceoud.data.remote.response.home.brands

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class HomeBrandsResponse(
    val status: Boolean,
    val message: String,
    val data: List<Brand>
)
@JsonClass(generateAdapter = true)

data class Brand(
    val id: Int,
    val name_en: String,
    val name_ar: String,
    val description_en: String,
    val description_ar: String,
    val deleted_at: String?,
    val created_at: String,
    val updated_at: String,
    val images: List<BrandImage>
)
@JsonClass(generateAdapter = true)

data class BrandImage(
    val id: Int,
    val path: String,
    val collection: String,
    val mime_type: String,
    val size: Int,
    val imageable_type: String,
    val imageable_id: Int,
    val created_at: String,
    val updated_at: String
)
