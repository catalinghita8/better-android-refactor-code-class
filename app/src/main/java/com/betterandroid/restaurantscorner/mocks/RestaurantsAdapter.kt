package com.betterandroid.restaurantscorner.mocks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.betterandroid.restaurantscorner.R
import com.betterandroid.restaurantscorner.Restaurant
import com.betterandroid.restaurantscorner.RestaurantDisplayItem
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantsAdapter() :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    var clickListener: RestaurantClickListener? = null
    var restaurants: List<RestaurantDisplayItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_restaurant,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            restaurants[position].let { restaurant ->
                restaurantName.text = restaurant.displayName
                restaurantDistance.text = restaurant.displayDistance

                Glide.with(context!!)
                    .load(restaurant.imageUrl)
                    .into(imageViewRestaurant)

                cardRestaurant.setOnClickListener {
                    clickListener?.onRestaurantClicked(restaurant.id)
                }
                
                when(restaurant.type) {
                    "TAKE_AWAY" -> {
                        restaurantFabIcon.setImageDrawable(context.getDrawable(R.drawable.take_away))
                        restaurantType.text = "Take away"
                        restaurantType.setTextColor(context.getColor(R.color.orange))
                    }
                    "EAT_IN" -> {
                        restaurantFabIcon.setImageDrawable(context.getDrawable(R.drawable.eat_in))
                        restaurantType.text = "Eat in"
                        restaurantType.setTextColor(context.getColor(R.color.brown))
                    }
                    "DRIVE_THROUGH" -> {
                        restaurantFabIcon.setImageDrawable(context.getDrawable(R.drawable.drive_through))
                        restaurantType.text = "Drive T"
                        restaurantType.setTextColor(context.getColor(R.color.colorAccent))
                    }
                }
            }

        }
    }

    override fun getItemCount() = restaurants.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface RestaurantClickListener {
        fun onRestaurantClicked(restaurantId: Int)
    }

}