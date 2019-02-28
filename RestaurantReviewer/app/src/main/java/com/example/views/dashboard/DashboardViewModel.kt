package com.example.views.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.model.Restaurant
import com.example.remote.BasicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(private val service: BasicService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(service) as T
    }
}

class DashboardViewModel(service: BasicService) : ViewModel() {

    val restaurants = MutableLiveData<List<Restaurant>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        //isLoading.postValue(true)
        service.getRestaurants().enqueue(object : Callback<ArrayList<Restaurant>> {
            override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
                //isLoading.postValue(false)
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        restaurants.postValue(it)
                    }
                }
                //isLoading.postValue(false)
            }
        })
    }

}
