package com.betterandroid.restaurantscorner

import com.betterandroid.restaurantscorner.mocks.MockCreator
import java.util.concurrent.TimeUnit

class RestaurantsRestClient {
    fun getRestaurants(userId: Int) =
        MockCreator.getRestaurantsResponseMock().delay(1000, TimeUnit.MILLISECONDS)
}