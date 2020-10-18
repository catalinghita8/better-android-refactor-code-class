package com.betterandroid.restaurantscorner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.betterandroid.restaurantscorner.mocks.MockCreator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_restaurants.*
import java.util.*

class RestaurantsActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()
    private var restaurantsAdapter: RestaurantsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        restaurantsAdapter = RestaurantsAdapter()
        recyclerViewRestaurants.apply {
            layoutManager = LinearLayoutManager(context!!,
                LinearLayoutManager.VERTICAL,
                false)
            this.adapter = restaurantsAdapter
        }
        showRestaurants()
    }

    private fun showRestaurants() {
        val client = RestaurantsRestClient()
        val userId = MockCreator.getUserId()
        disposable.add(
            client.getRestaurants(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val restaurants = response.restaurants
                    val parsedRestaurants = arrayListOf<Restaurant>()

                    if (restaurants != null) {
                        for (responseRestaurant in restaurants) {
                            if (responseRestaurant.name != null
                                && responseRestaurant.imageUrl != null) {
                                val location = SimpleLocation(
                                    responseRestaurant.locationLatitude,
                                    responseRestaurant.locationLongitude
                                )
                                parsedRestaurants.add(
                                    Restaurant(
                                        id = responseRestaurant.id,
                                        name = responseRestaurant.name,
                                        imageUrl = responseRestaurant.imageUrl,
                                        location = location,
                                        closingHour = responseRestaurant.closingHour,
                                        type = responseRestaurant.type
                                    )
                                )
                            }
                        }
                    }

                    val filteredRestaurants =  arrayListOf<Restaurant> ()
                    for (parsedRestaurant in parsedRestaurants) {
                        if (parsedRestaurant.closingHour < 6)
                            filteredRestaurants.add(parsedRestaurant)
                    }

                    // val latitude = MockCreator.getUserLatitude()
                    for (filteredRestaurant in filteredRestaurants) {
                        val userLat = MockCreator.getUserLatitude()
                        val userLong = MockCreator.getUserLongitude()

                        val R = 6371 // Radius of the earth
                        val latDistance = Math.toRadians(userLat
                                - filteredRestaurant.location.latitude)
                        val lonDistance = Math.toRadians(userLong
                                - filteredRestaurant.location.longitude)
                        val a = (Math.sin(latDistance / 2)
                                * Math.sin(latDistance / 2)
                                + (Math.cos(Math.toRadians(filteredRestaurant.location.latitude))
                                * Math.cos(Math.toRadians(userLat))
                                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)))
                        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
                        val distance = R * c
                        Log.d("DISTANCE_LOGS", "found distance at $distance")
                        filteredRestaurant.distance = Math.sqrt(
                            Math.pow(distance, 2.0) + 0.0).toInt()
                    }
                    Collections.sort(filteredRestaurants, RestaurantDistanceSorter())

                    val displayRestaurants= arrayListOf<RestaurantDisplayItem>()
                    filteredRestaurants.forEach { restaurant ->
                        displayRestaurants.add(
                            RestaurantDisplayItem(
                                id = restaurant.id ,
                                displayName = "Restaurant ${restaurant.name}",
                                displayDistance = "at ${restaurant.distance} KM distance",
                                imageUrl = restaurant.imageUrl,
                                type = restaurant.type
                           )
                        )
                    }

                    val adapter = restaurantsAdapter
                    if(adapter != null) {
                        adapter.restaurants = displayRestaurants
                        adapter.clickListener =
                            object : RestaurantsAdapter.RestaurantClickListener {
                                override fun onRestaurantClicked(restaurantId: Int) {
                                    Toast.makeText(
                                        this@RestaurantsActivity,
                                        "Pressed a restaurant!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                }, {})
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}