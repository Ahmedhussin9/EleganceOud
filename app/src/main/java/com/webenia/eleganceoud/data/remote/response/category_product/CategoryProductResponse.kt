package com.webenia.eleganceoud.data.remote.response.category_product

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class CategoryProductResponse(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Data(

	@Json(name="images")
	val images: List<ImagesItem?>? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="description_en")
	val descriptionEn: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="deleted_at")
	val deletedAt: String? = null,

	@Json(name="brand_id")
	val brandId: Int? = null,

	@Json(name="products")
	val products: List<ProductsItem?>? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="brand")
	val brand: Brand? = null,

	@Json(name="slug")
	val slug: String? = null,

	@Json(name="name_en")
	val nameEn: String? = null,

	@Json(name="description_ar")
	val descriptionAr: String? = null
)

@JsonClass(generateAdapter = true)
data class Brand(

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="description_en")
	val descriptionEn: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="deleted_at")
	val deletedAt: String? = null,

	@Json(name="name_en")
	val nameEn: String? = null,

	@Json(name="description_ar")
	val descriptionAr: String? = null
)

@JsonClass(generateAdapter = true)
data class ProductsItem(

	@Json(name="images")
	val images: List<ImagesItem?>? = null,

	@Json(name="show_on_home_page")
	val showOnHomePage: Int? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="description_en")
	val descriptionEn: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="discount")
	val discount: Discount? = null,

	@Json(name="deleted_at")
	val deletedAt: String? = null,

	@Json(name="currency_code")
	val currencyCode: String? = null,

	@Json(name="is_available")
	val isAvailable: Int? = null,

	@Json(name="brand_id")
	val brandId: Int? = null,

	@Json(name="discounted_price")
	val discountedPrice: Double? = null,

	@Json(name="category_id")
	val categoryId: Int? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="parent_id")
	val parentId: Int? = null,

	@Json(name="converted_price")
	val convertedPrice: Double? = null,

	@Json(name="currency")
	val currency: Currency? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="currency_id")
	val currencyId: Int? = null,

	@Json(name="country_id")
	val countryId: Int? = null,

	@Json(name="name_en")
	val nameEn: String? = null,

	@Json(name="description_ar")
	val descriptionAr: String? = null,
	@Json(name = "is_favorite")
	val isFavorite: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Currency(

	@Json(name="code")
	val code: String? = null,

	@Json(name="is_deleted")
	val isDeleted: Boolean? = null,

	@Json(name="exchange_rate")
	val exchangeRate: String? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="name_en")
	val nameEn: String? = null
)

@JsonClass(generateAdapter = true)
data class ImagesItem(

	@Json(name="path")
	val path: String? = null,

	@Json(name="size")
	val size: Int? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="mime_type")
	val mimeType: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="collection")
	val collection: String? = null,

	@Json(name="imageable_id")
	val imageableId: Int? = null,

	@Json(name="imageable_type")
	val imageableType: String? = null
)
@JsonClass(generateAdapter = true)
data class Discount(
	@Json(name = "id") val id: Int? = null,
	@Json(name = "start_date") val startDate: String? = null,
	@Json(name = "duration") val duration: Int? = null,
	@Json(name = "discount_value") val discountValue: String?,
	@Json(name = "is_active") val isActive: Boolean? = null,
	@Json(name = "product_id") val productId: Int? = null,
	@Json(name = "category_id") val categoryId: Int? = null,
	@Json(name = "end_date") val endDate: String? = null,
	@Json(name = "created_at") val createdAt: String? = null,
	@Json(name = "updated_at") val updatedAt: String? = null
)
