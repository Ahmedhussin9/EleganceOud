package com.webenia.eleganceoud.data.remote.response.home.best_sellings

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeBestSellingResponse(
    val status: Boolean,
    val message: String,
    val data: List<Product>
)
@JsonClass(generateAdapter = true)
data class Product(
    val id: Int,
    val name_ar: String,
    val name_en: String,
    val description_ar: String?,
    val description_en: String?,
    val price: String,
    val is_available: Int,
    val show_on_home_page: Int,
    val category_id: Int?,
    val currency_id: Int,
    val country_id: Int,
    val parent_id: Int?,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String?,
    val total_quantity: String? = null,
    val converted_price: Double,
    val currency_code: String,
    val images: List<Image>? = emptyList(),
    val currency: Currency,
    val category: Category? = null,
    val children: List<Product> = emptyList() // Recursive relationship
)
@JsonClass(generateAdapter = true)
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
@JsonClass(generateAdapter = true)
data class Currency(
    val id: Int,
    val name_ar: String,
    val name_en: String,
    val code: String,
    val exchange_rate: String,
    val is_deleted: Boolean
)
@JsonClass(generateAdapter = true)
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
    val description: String?
)
