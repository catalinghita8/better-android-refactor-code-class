package com.betterandroid.restaurantscorner

import android.location.Location
import com.betterandroid.restaurantscorner.mocks.MockCreator

class RestaurantRules {
    fun filterRestaurants(restaurants: List<Restaurant>): List<Restaurant> {
        return restaurants
            .filter { restaurant -> restaurant.closingHour < 6 }
            .map { restaurant ->
                val userLat = MockCreator.getUserLatitude()
                val userLong = MockCreator.getUserLongitude()
                val distance = FloatArray(2)
                Location.distanceBetween(
                    userLat, userLong,
                    restaurant.location.latitude,
                    restaurant.location.longitude,
                    distance
                )
                val distanceResult = distance[0] / 1000
                restaurant.distance = distanceResult.toInt()
                return@map restaurant
            }.sortedBy { restaurant -> restaurant.distance }
    }
}