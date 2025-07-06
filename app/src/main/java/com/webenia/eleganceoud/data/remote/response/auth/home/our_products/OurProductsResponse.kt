package com.webenia.eleganceoud.data.remote.response.auth.home.our_products

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Product>
)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("description_ar") val descriptionAr: String,
    @SerializedName("description_en") val descriptionEn: String,
    @SerializedName("price") val price: String,
    @SerializedName("is_available") val isAvailable: Int,
    @SerializedName("show_on_home_page") val showOnHomePage: Int,
    @SerializedName("category_id") val categoryId: Int?,
    @SerializedName("currency_id") val currencyId: Int,
    @SerializedName("country_id") val countryId: Int,
    @SerializedName("parent_id") val parentId: Int?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("deleted_at") val deletedAt: String?,
    @SerializedName("converted_price") val convertedPrice: Int,
    @SerializedName("currency_code") val currencyCode: String,
    @SerializedName("images") val images: List<ProductImage> = emptyList(),
    @SerializedName("currency") val currency: Currency,
    @SerializedName("category") val category: Category? = null,
    @SerializedName("amounts") val amounts: List<ProductAmount> = emptyList(),
    @SerializedName("parent") val parent: ParentProduct? = null,
    @SerializedName("discount") val discount: Any? = null
)

data class ProductImage(
    @SerializedName("id") val id: Int,
    @SerializedName("path") val path: String,
    @SerializedName("collection") val collection: String,
    @SerializedName("mime_type") val mimeType: String,
    @SerializedName("size") val size: Int,
    @SerializedName("imageable_type") val imageableType: String,
    @SerializedName("imageable_id") val imageableId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class Currency(
    @SerializedName("id") val id: Int,
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("code") val code: String,
    @SerializedName("exchange_rate") val exchangeRate: String,
    @SerializedName("is_deleted") val isDeleted: Boolean
)

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("description_en") val descriptionEn: String?,
    @SerializedName("description_ar") val descriptionAr: String?,
    @SerializedName("brand_id") val brandId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("deleted_at") val deletedAt: String?,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?
)

data class ProductAmount(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: String,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("unit_id") val unitId: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class ParentProduct(
    @SerializedName("id") val id: Int,
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("description_ar") val descriptionAr: String,
    @SerializedName("description_en") val descriptionEn: String,
    @SerializedName("price") val price: String,
    @SerializedName("is_available") val isAvailable: Int,
    @SerializedName("show_on_home_page") val showOnHomePage: Int,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("currency_id") val currencyId: Int,
    @SerializedName("country_id") val countryId: Int,
    @SerializedName("parent_id") val parentId: Int?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("deleted_at") val deletedAt: String?,
    @SerializedName("converted_price") val convertedPrice: Int,
    @SerializedName("currency_code") val currencyCode: String,
    @SerializedName("currency") val currency: Currency
)
