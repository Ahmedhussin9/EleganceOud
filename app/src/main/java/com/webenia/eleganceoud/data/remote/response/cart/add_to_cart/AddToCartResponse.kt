package com.webenia.eleganceoud.data.remote.response.cart.add_to_cart

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class AddToCartResponse(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Product(

	@Json(name="is_favorite")
	val isFavorite: Boolean? = null,

	@Json(name="show_on_home_page")
	val showOnHomePage: Int? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="description_en")
	val descriptionEn: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="discount")
	val discount: Any? = null,

	@Json(name="deleted_at")
	val deletedAt: Any? = null,

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
	val parentId: Any? = null,

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
	val descriptionAr: String? = null
)

@JsonClass(generateAdapter = true)
data class Data(

	@Json(name="product")
	val product: Product? = null,

	@Json(name="quantity")
	val quantity: Int? = null,

	@Json(name="discount")
	val discount: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="converted_total")
	val convertedTotal: Double? = null,

	@Json(name="currency_code")
	val currencyCode: String? = null,

	@Json(name="amount_id")
	val amountId: Any? = null,

	@Json(name="cart_id")
	val cartId: Int? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="product_id")
	val productId: Int? = null,

	@Json(name="converted_price")
	val convertedPrice: Double? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="converted_discount")
	val convertedDiscount: Double? = null
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
