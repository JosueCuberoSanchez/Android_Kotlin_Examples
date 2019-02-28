package com.example.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.model.Restaurant
import kotlinx.android.synthetic.main.restaurant_item.view.*
import com.bumptech.glide.Glide
import com.example.restaurantreviewer.R

class RestaurantAdapter(private var items: List<Restaurant> = emptyList(),
                        private val listener: (String) -> Unit):
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): RestaurantAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.
            layout.restaurant_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.bind(items[index], listener)
    }

    fun update(restaurants: List<Restaurant>) {
        items = restaurants
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant, listener: (String) -> Unit) {
            itemView.restaurant_name.text = restaurant.name
            itemView.restaurant_location.text = restaurant.location.address
            itemView.setOnClickListener { listener(restaurant.id) }

            Glide.with(itemView).load(restaurant.location.photos[0].url).into(itemView.restaurant_image)
        }
    }
}