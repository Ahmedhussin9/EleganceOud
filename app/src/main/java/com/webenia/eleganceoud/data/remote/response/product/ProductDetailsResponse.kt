import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    @Json(name = "status") val status: Boolean? = null,
    @Json(name = "message") val message: String? = null,
    @Json(name = "data") val data: Product? = null
)

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "description_ar") val descriptionAr: String? = null,
    @Json(name = "description_en") val descriptionEn: String? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "is_available") val isAvailable: Int? = null,
    @Json(name = "show_on_home_page") val showOnHomePage: Int? = null,
    @Json(name = "category_id") val categoryId: Int? = null,
    @Json(name = "currency_id") val currencyId: Int? = null,
    @Json(name = "country_id") val countryId: Int? = null,
    @Json(name = "parent_id") val parentId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "converted_price") val convertedPrice: Double? = null,
    @Json(name = "currency_code") val currencyCode: String? = null,
    @Json(name = "images") val images: List<ProductImage>? = null,
    @Json(name = "currency") val currency: Currency? = null,
    @Json(name = "category") val category: Category? = null,
    @Json(name = "amounts") val amounts: List<Amount>? = null,
    @Json(name = "discount") val discount: Discount? = null,
    @Json(name = "parent") val parent: ParentProduct? = null,
    @Json(name = "children") val children: List<Child>? = null,
    @Json(name = "discounted_price") val priceAfterDiscount: Double? = null,

    )

@JsonClass(generateAdapter = true)
data class ProductImage(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "path") val path: String? = null,
    @Json(name = "collection") val collection: String? = null,
    @Json(name = "mime_type") val mimeType: String? = null,
    @Json(name = "size") val size: Int? = null,
    @Json(name = "imageable_type") val imageableType: String? = null,
    @Json(name = "imageable_id") val imageableId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null
)

@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "code") val code: String? = null,
    @Json(name = "exchange_rate") val exchangeRate: String? = null,
    @Json(name = "is_deleted") val isDeleted: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "slug") val slug: String? = null,
    @Json(name = "description_en") val descriptionEn: String? = null,
    @Json(name = "description_ar") val descriptionAr: String? = null,
    @Json(name = "brand_id") val brandId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null
)

@JsonClass(generateAdapter = true)
data class Amount(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "product_id") val productId: Int? = null,
    @Json(name = "unit_id") val unitId: Int? = null,
    @Json(name = "weight") val weight: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "discounted_price") val discountedPrice: String? = null,
    @Json(name = "product") val product: AmountProductItem? = null,
    @Json(name = "unit") val unit: UnitInfo? = null
)
@JsonClass(generateAdapter = true)
data class AmountProductItem(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "description_ar") val descriptionAr: String? = null,
    @Json(name = "description_en") val descriptionEn: String? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "is_available") val isAvailable: Int? = null,
    @Json(name = "show_on_home_page") val showOnHomePage: Int? = null,
    @Json(name = "category_id") val categoryId: Int? = null,
    @Json(name = "currency_id") val currencyId: Int? = null,
    @Json(name = "country_id") val countryId: Int? = null,
    @Json(name = "parent_id") val parentId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "converted_price") val convertedPrice: Double? = null,
    @Json(name = "currency_code") val currencyCode: String? = null,
    @Json(name = "images") val images: List<ProductImage>? = null,
    @Json(name = "currency") val currency: Currency? = null,
    @Json(name = "category") val category: Category? = null,
    @Json(name = "amounts") val amounts: List<Amount>? = null,
    @Json(name = "discount") val discount: Discount? = null,
    @Json(name = "parent") val parent: ParentProduct? = null,
    @Json(name = "children") val children: List<Child>? = null,
    @Json(name = "discounted_price") val priceAfterDiscount: Double? = null,

    )
@JsonClass(generateAdapter = true)
data class UnitInfo(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null
)

@JsonClass(generateAdapter = true)
data class Discount(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "start_date") val startDate: String? = null,
    @Json(name = "duration") val duration: Int? = null,
    @Json(name = "discount_value") val discountValue: String? = null,
    @Json(name = "is_active") val isActive: Boolean? = null,
    @Json(name = "product_id") val productId: Int? = null,
    @Json(name = "category_id") val categoryId: Int? = null,
    @Json(name = "end_date") val endDate: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null
)

@JsonClass(generateAdapter = true)
data class ParentProduct(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "description_ar") val descriptionAr: String? = null,
    @Json(name = "description_en") val descriptionEn: String? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "is_available") val isAvailable: Int? = null,
    @Json(name = "show_on_home_page") val showOnHomePage: Int? = null,
    @Json(name = "category_id") val categoryId: Int? = null,
    @Json(name = "currency_id") val currencyId: Int? = null,
    @Json(name = "country_id") val countryId: Int? = null,
    @Json(name = "parent_id") val parentId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "converted_price") val convertedPrice: Double? = null,
    @Json(name = "currency_code") val currencyCode: String? = null,
    @Json(name = "currency") val currency: Currency? = null,
    @Json(name = "images") val image: String? = null,
)

@JsonClass(generateAdapter = true)
data class Child(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "description_ar") val descriptionAr: String? = null,
    @Json(name = "description_en") val descriptionEn: String? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "is_available") val isAvailable: Int? = null,
    @Json(name = "show_on_home_page") val showOnHomePage: Int? = null,
    @Json(name = "category_id") val categoryId: Int? = null,
    @Json(name = "brand_id") val brandId: Int? = null,
    @Json(name = "currency_id") val currencyId: Int? = null,
    @Json(name = "country_id") val countryId: Int? = null,
    @Json(name = "parent_id") val parentId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "deleted_at") val deletedAt: String? = null,
    @Json(name = "converted_price") val convertedPrice: Double? = null,
    @Json(name = "currency_code") val currencyCode: String? = null,
    @Json(name = "images") val images: List<Image>? = null,
    @Json(name = "currency") val currency: CurrencyChild? = null,
    @Json(name = "discounted_price") val priceAfterDiscount: Double? = null,
    @Json(name = "discount") val discount: Discount? = null
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "path") val path: String? = null,
    @Json(name = "collection") val collection: String? = null,
    @Json(name = "mime_type") val mimeType: String? = null,
    @Json(name = "size") val size: Int? = null,
    @Json(name = "imageable_type") val imageableType: String? = null,
    @Json(name = "imageable_id") val imageableId: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null
)

@JsonClass(generateAdapter = true)
data class CurrencyChild(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name_ar") val nameAr: String? = null,
    @Json(name = "name_en") val nameEn: String? = null,
    @Json(name = "code") val code: String? = null,
    @Json(name = "exchange_rate") val exchangeRate: String? = null,
    @Json(name = "is_deleted") val isDeleted: Boolean? = null
)
