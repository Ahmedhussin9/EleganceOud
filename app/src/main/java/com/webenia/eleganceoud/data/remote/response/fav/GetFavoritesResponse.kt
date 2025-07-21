package com.webenia.eleganceoud.data.remote.response.fav

import com.squareup.moshi.JsonClass
import android.os.Parcelable
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class GetFavoritesResponse(

	@Json(name="GetFavoritesResponse")
	val getFavoritesResponse: List<GetFavoritesResponseItem?>? = null
)

@JsonClass(generateAdapter = true)
data class Discount(

	@Json(name="type")
	val type: Any? = null,

	@Json(name="value")
	val value: String? = null
)

@JsonClass(generateAdapter = true)
data class GetFavoritesResponseItem(

	@Json(name="images")
	val images: List<String?>? = null,

	@Json(name="price")
	val price: String? = null,

	@Json(name="product_id")
	val productId: Int? = null,

	@Json(name="name_ar")
	val nameAr: String? = null,

	@Json(name="discount")
	val discount: Discount? = null,

	@Json(name="converted_price")
	val convertedPrice: Double? = null,

	@Json(name="favorite_id")
	val favoriteId: Int? = null,

	@Json(name="price_after_discount")
	val priceAfterDiscount: String? = null,

	@Json(name="currency_code")
	val currencyCode: String? = null,

	@Json(name="name_en")
	val nameEn: String? = null,

	@Json(name="is_available")
	val isAvailable: Int? = null
)
