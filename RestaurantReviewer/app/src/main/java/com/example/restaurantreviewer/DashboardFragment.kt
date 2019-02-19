package com.example.restaurantreviewer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remote.BasicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.model.Restaurant


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getRestaurants()
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    private fun getRestaurants() {
        BasicService.instance.getRestaurants().enqueue(object: Callback<ArrayList<Restaurant>> {
            override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
                when (response.isSuccessful) {
                    true -> showResult(response.code().toString())
                    false -> showError(response.code().toString())
                }
            }
            override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
                showError(t.localizedMessage)
            }
        })
    }

    fun showResult(title: String?) {
        Log.d("Show result",title)
    }

    fun showError(error: String?) {
        Log.d("Error", error)
    }

}
