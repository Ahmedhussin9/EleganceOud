package com.webenia.eleganceoud.data.remote.response.cart.get_cart

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class GetCartResponse(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Headers(
	val any: Any? = null
)

@JsonClass(generateAdapter = true)
data class Currency(

	@Json(name="code")
	val code: String? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="name_en")
	val nameEn: String? = null
)

@JsonClass(generateAdapter = true)
data class Data(

	@Json(name="exception")
	val exception: Any? = null,

	@Json(name="headers")
	val headers: Headers? = null,

	@Json(name="original")
	val original: Original? = null
)

@JsonClass(generateAdapter = true)
data class Product(

	@Json(name="price")
	val price: String? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="discount")
	val discount: Discount? = null,

	@Json(name="converted_price")
	val convertedPrice: Double? = null,

	@Json(name="converted_total")
	val convertedTotal: Double? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="converted_discount")
	val convertedDiscount: Double? = null,

	@Json(name="price_after_discount")
	val priceAfterDiscount: String? = null,

	@Json(name="currency_code")
	val currencyCode: String? = null,

	@Json(name="name_en")
	val nameEn: String? = null
)

@JsonClass(generateAdapter = true)
data class DataItem(

	@Json(name="product")
	val product: Product? = null,

	@Json(name="images")
	val images: List<String?>? = null,

	@Json(name="quantity")
	val quantity: Int? = null,

	@Json(name="discount")
	val discount: String? = null,

	@Json(name="converted_total")
	val convertedTotal: Double? = null,

	@Json(name="currency_code")
	val currencyCode: String? = null,

	@Json(name="cart_id")
	val cartId: Int? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="product_id")
	val productId: Int? = null,

	@Json(name="converted_price")
	val convertedPrice: Double? = null,

	@Json(name="currency")
	val currency: Currency? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="converted_discount")
	val convertedDiscount: Double? = null
)

@JsonClass(generateAdapter = true)
data class Original(

	@Json(name="data")
	val data: List<DataItem?>? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: Boolean? = null
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