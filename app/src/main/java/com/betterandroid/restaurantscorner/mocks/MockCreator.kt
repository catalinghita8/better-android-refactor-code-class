package com.betterandroid.restaurantscorner.mocks

import com.betterandroid.restaurantscorner.RestaurantListResponse
import com.betterandroid.restaurantscorner.RestaurantResponse
import io.reactivex.Single

/**
 * This class provides mocks data. Ignore it.
 */
object MockCreator {

    fun getRestaurantsResponseMock() = Single.just(getMockRestaurantResponse())

    fun getUserLatitude() = 51.0000
    fun getUserLongitude() = -0.10000

    private fun getMockRestaurantResponse(): RestaurantListResponse {
        val restaurants = arrayListOf<RestaurantResponse>()
        restaurants.add(
            RestaurantResponse(
                1023,
                "McDonalds U.K.",
                "https://vignette.wikia.nocookie.net/cityville/images/a/a4/McDonald%27s_Restaurant.png/revision/latest/scale-to-width-down/340?cb=20111019142537",
                51.609865,
                -0.128032,
                3,
                "EAT_IN"
            )
        )
        restaurants.add(
            RestaurantResponse(
                1023,
                "Burger King",
                "https://vignette.wikia.nocookie.net/cityville/images/7/7f/Burger_Restaurant-SE.png/revision/latest/scale-to-width-down/155?cb=20111110015150",
                50.509865,
                -1.018092,
                4,
                "TAKE_AWAY"
            )
        )
        restaurants.add(
            RestaurantResponse(
                1023,
                "McDonalds IRE",
                "https://vignette.wikia.nocookie.net/cityville/images/3/31/Downtown_Holiday_Restaurant-NW.png/revision/latest/scale-to-width-down/155?cb=20111209044532",
                52.109000,
                -1.12833,
                5,
                "DRIVE_THROUGH"
            )
        )
        restaurants.add(
            RestaurantResponse(
                1023,
                "McDonalds FR.",
                "https://vignette.wikia.nocookie.net/cityville/images/9/9b/Summer_in_the_City_McDonald%27s-SE.png/revision/latest?cb=20120515210536",
                51.444555,
                -0.213334,
                3,
                "TAKE_AWAY"

            )
        )
        return RestaurantListResponse(restaurants)
    }

    fun getUserId(): Int = 100

}