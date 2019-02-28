package com.example.views.restaurant

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.model.Restaurant
import com.example.remote.BasicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class RestaurantViewModelFactory(private val service: BasicService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantViewModel(service) as T
    }
}

class RestaurantViewModel(service: BasicService) : ViewModel() {

    val service: BasicService
    val errorMessage = MutableLiveData<String>()
    val restaurant = MutableLiveData<Restaurant>()

    init { this.service = service }

    fun getRestaurant(restaurantId: String) {
        service.getRestaurant(restaurantId).enqueue(object : Callback<Restaurant> {
            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    response.body().let {
                        restaurant.postValue(it)
                    }
                }
            }
        })
    }

}