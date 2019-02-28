package com.example.views.restaurant

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.remote.BasicService

import com.example.restaurantreviewer.R
import kotlinx.android.synthetic.main.fragment_restaurant.*

private const val ITEM_ID = "item_id"

class RestaurantFragment : Fragment() {

    private var restaurantId: String? = null

    private val viewModel by lazy {
        ViewModelProviders.of(this, RestaurantViewModelFactory(BasicService.instance))
            .get(RestaurantViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurantId = it.getString(ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantId?.let {
            viewModel.getRestaurant(it)
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.restaurant.observe(this, Observer {
            it?.let { restaurant ->
                restaurant_name.text = restaurant.name
                restaurant_location.text = restaurant.location.address
                this.view?.let {
                    Glide.with(it).load(restaurant.location.photos[0].url).into(restaurant_image)
                }
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            it?.let { error ->
                Log.d("Error:", error)
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ITEM_ID, itemId)
                }
            }
    }
}
