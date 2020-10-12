package com.betterandroid.restaurantscorner

import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(val restaurants: List<RestaurantResponse>?)

data class RestaurantResponse(
    @SerializedName("identifier") val id: Int,
    @SerializedName("rest_name") val name: String?,
    @SerializedName("rest_pic") val imageUrl: String?,
    @SerializedName("lat") val locationLatitude: Double,
    @SerializedName("lng") val locationLongitude: Double,
    @SerializedName("closing_time") val closingHour: Int,
    @SerializedName("rest_type") val type: String,
)