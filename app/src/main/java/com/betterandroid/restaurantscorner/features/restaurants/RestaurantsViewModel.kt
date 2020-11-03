package com.betterandroid.restaurantscorner.features.restaurants

import com.betterandroid.restaurantscorner.domain.models.restaurants.Restaurant
import com.betterandroid.restaurantscorner.domain.models.restaurants.RestaurantDisplayItem
import com.betterandroid.restaurantscorner.domain.models.restaurants.RestaurantType

class RestaurantsViewModel {

    fun getDisplayRestaurants(restaurants: List<Restaurant>): List<RestaurantDisplayItem> {
        return restaurants.map { restaurant ->
            return@map RestaurantDisplayItem(
                id = restaurant.id,
                displayName = "Restaurant ${restaurant.name}",
                displayDistance = "at ${restaurant.distance} KM distance",
                imageUrl = restaurant.imageUrl,
                type = when (restaurant.type) {
                    "EAT_IN" -> RestaurantType.EAT_IN
                    "TAKE_AWAY" -> RestaurantType.TAKE_AWAY
                    else -> RestaurantType.DRIVE_THROUGH
                }
            )
        }
    }

}