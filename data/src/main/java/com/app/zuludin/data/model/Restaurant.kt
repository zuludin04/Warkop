package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class Restaurant(

	@field:SerializedName("include_bogo_offers")
	val includeBogoOffers: Boolean? = null,

	@field:SerializedName("has_online_delivery")
	val hasOnlineDelivery: Int? = null,

	@field:SerializedName("establishment_types")
	val establishmentTypes: List<Any>? = null,

	@field:SerializedName("has_table_booking")
	val hasTableBooking: Int? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("average_cost_for_two")
	val averageCostForTwo: Int? = null,

	@field:SerializedName("menu_url")
	val menuUrl: String? = null,

	@field:SerializedName("price_range")
	val priceRange: Int? = null,

	@field:SerializedName("switch_to_order_menu")
	val switchToOrderMenu: Int? = null,

	@field:SerializedName("photos")
	val photos: List<PhotosItem>? = null,

	@field:SerializedName("R")
	val R: Ray? = null,

	@field:SerializedName("all_reviews_count")
	val allReviewsCount: Int? = null,

	@field:SerializedName("is_table_reservation_supported")
	val isTableReservationSupported: Int? = null,

	@field:SerializedName("timings")
	val timings: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("opentable_support")
	val opentableSupport: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("all_reviews")
	val allReviews: AllReviews? = null,

	@field:SerializedName("user_rating")
	val userRating: UserRating? = null,

	@field:SerializedName("offers")
	val offers: List<Any>? = null,

	@field:SerializedName("apikey")
	val apikey: String? = null,

	@field:SerializedName("is_delivering_now")
	val isDeliveringNow: Int? = null,

	@field:SerializedName("deeplink")
	val deeplink: String? = null,

	@field:SerializedName("is_zomato_book_res")
	val isZomatoBookRes: Int? = null,

	@field:SerializedName("establishment")
	val establishment: List<String>? = null,

	@field:SerializedName("featured_image")
	val featuredImage: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("cuisines")
	val cuisines: String? = null,

	@field:SerializedName("phone_numbers")
	val phoneNumbers: String? = null,

	@field:SerializedName("photo_count")
	val photoCount: Int? = null,

	@field:SerializedName("highlights")
	val highlights: List<String>? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("book_again_url")
	val bookAgainUrl: String? = null,

	@field:SerializedName("is_book_form_web_view")
	val isBookFormWebView: Int? = null,

	@field:SerializedName("book_form_web_view_url")
	val bookFormWebViewUrl: String? = null,

	@field:SerializedName("mezzo_provider")
	val mezzoProvider: String? = null,

	@field:SerializedName("photos_url")
	val photosUrl: String? = null,

	@field:SerializedName("book_url")
	val bookUrl: String? = null,

	@field:SerializedName("medio_provider")
	val medioProvider: String? = null,

	@field:SerializedName("zomato_events")
	val zomatoEvents: List<ZomatoEventsItem>? = null
)