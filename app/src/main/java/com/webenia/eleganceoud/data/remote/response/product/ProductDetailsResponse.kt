import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    @Json(name = "status") val status: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: Product
)

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id") val id: Int,
    @Json(name = "name_ar") val nameAr: String,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "description_ar") val descriptionAr: String,
    @Json(name = "description_en") val descriptionEn: String,
    @Json(name = "price") val price: String,
    @Json(name = "is_available") val isAvailable: Int,
    @Json(name = "show_on_home_page") val showOnHomePage: Int,
    @Json(name = "category_id") val categoryId: Int,
    @Json(name = "currency_id") val currencyId: Int,
    @Json(name = "country_id") val countryId: Int,
    @Json(name = "parent_id") val parentId: Int?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "deleted_at") val deletedAt: String?,
    @Json(name = "converted_price") val convertedPrice: Double,
    @Json(name = "currency_code") val currencyCode: String,
    @Json(name = "images") val images: List<ProductImage>?,
    @Json(name = "currency") val currency: Currency,
    @Json(name = "category") val category: Category,
    @Json(name = "amounts") val amounts: List<Amount>,
    @Json(name = "discount") val discount: Discount?,
    @Json(name = "parent") val parent: ParentProduct?,
    @Json(name = "children") val children: List<Product> = emptyList()


)

@JsonClass(generateAdapter = true)
data class ProductImage(
    @Json(name = "id") val id: Int,
    @Json(name = "path") val path: String,
    @Json(name = "collection") val collection: String,
    @Json(name = "mime_type") val mimeType: String,
    @Json(name = "size") val size: Int,
    @Json(name = "imageable_type") val imageableType: String,
    @Json(name = "imageable_id") val imageableId: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "id") val id: Int,
    @Json(name = "name_ar") val nameAr: String,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "code") val code: String,
    @Json(name = "exchange_rate") val exchangeRate: String,
    @Json(name = "is_deleted") val isDeleted: Boolean
)

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id") val id: Int,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "name_ar") val nameAr: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "description_en") val descriptionEn: String?,
    @Json(name = "description_ar") val descriptionAr: String?,
    @Json(name = "brand_id") val brandId: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "deleted_at") val deletedAt: String?,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?
)

@JsonClass(generateAdapter = true)
data class Amount(
    @Json(name = "id") val id: Int,
    @Json(name = "price") val price: String,
    @Json(name = "product_id") val productId: Int,
    @Json(name = "unit_id") val unitId: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "unit") val unit: UnitInfo
)

@JsonClass(generateAdapter = true)
data class UnitInfo(
    @Json(name = "id") val id: Int,
    @Json(name = "name_ar") val nameAr: String,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "deleted_at") val deletedAt: String?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

@JsonClass(generateAdapter = true)
data class Discount(
    @Json(name = "id") val id: Int,
    @Json(name = "start_date") val startDate: String,
    @Json(name = "duration") val duration: Int,
    @Json(name = "discount_value") val discountValue: String,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "product_id") val productId: Int,
    @Json(name = "category_id") val categoryId: Int,
    @Json(name = "end_date") val endDate: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

@JsonClass(generateAdapter = true)
data class ParentProduct(
    @Json(name = "id") val id: Int,
    @Json(name = "name_ar") val nameAr: String,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "description_ar") val descriptionAr: String,
    @Json(name = "description_en") val descriptionEn: String,
    @Json(name = "price") val price: String,
    @Json(name = "is_available") val isAvailable: Int,
    @Json(name = "show_on_home_page") val showOnHomePage: Int,
    @Json(name = "category_id") val categoryId: Int,
    @Json(name = "currency_id") val currencyId: Int,
    @Json(name = "country_id") val countryId: Int,
    @Json(name = "parent_id") val parentId: Int?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "deleted_at") val deletedAt: String?,
    @Json(name = "converted_price") val convertedPrice: Double,
    @Json(name = "currency_code") val currencyCode: String,
    @Json(name = "currency") val currency: Currency
)
