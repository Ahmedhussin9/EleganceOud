package com.webenia.eleganceoud.data.remote.response.home.category

data class CategoriesResponse (
    val status: Boolean,
    val message: String,
    val data: List<Category>
)
data class Category(
    val id: Int,
    val name_en: String,
    val name_ar: String,
    val slug: String,
    val description_en: String?,
    val description_ar: String?,
    val brand_id: Int,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String?,
    val name: String,
    val description: String?,
    val brand: Brand,
    val images: List<Image>
)
data class Brand(
    val id: Int,
    val name_en: String,
    val name_ar: String,
    val description_en: String,
    val description_ar: String,
    val deleted_at: String?,
    val created_at: String,
    val updated_at: String
)
data class Image(
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
