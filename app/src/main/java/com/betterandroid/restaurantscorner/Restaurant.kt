package com.betterandroid.restaurantscorner

data class Restaurant(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val location: SimpleLocation,
    val closingHour: Int,
    var distance: Int = 0,
    var type: String,
)

class RestaurantDistanceSorter : Comparator<Restaurant> {
    override fun compare(p0: Restaurant?, p1: Restaurant?): Int {
        return p0!!.distance - p1!!.distance
    }
}

data class SimpleLocation(
    val latitude: Double,
    val longitude: Double,
)
