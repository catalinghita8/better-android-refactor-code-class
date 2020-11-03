package com.betterandroid.restaurantscorner.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.betterandroid.restaurantscorner.R
import com.betterandroid.restaurantscorner.domain.models.restaurants.RestaurantDisplayItem
import com.bumptech.glide.Glide
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

                restaurantFabIcon.setImageDrawable(ContextCompat.getDrawable(context, restaurant.type.drawableId))
                restaurantType.text = restaurant.type.text
                restaurantType.setTextColor(context.getColor(restaurant.type.textColorId))
            }

        }
    }

    override fun getItemCount() = restaurants.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface RestaurantClickListener {
        fun onRestaurantClicked(restaurantId: Int)
    }

}