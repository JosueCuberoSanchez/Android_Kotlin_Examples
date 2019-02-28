package com.example.views.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.adapter.RestaurantAdapter
import com.example.remote.BasicService
import com.example.restaurantreviewer.R
import com.example.views.restaurant.RestaurantFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private val restaurantAdapter = RestaurantAdapter(listener = { itemId: String -> itemClicked(itemId) })

    private val viewModel by lazy {
        ViewModelProviders.of(this, DashboardViewModelFactory(BasicService.instance))
            .get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        restaurantList.adapter = restaurantAdapter
        restaurantList.layoutManager = GridLayoutManager(this.context, resources.getInteger(R.integer.grid_column_count))
        restaurantList.addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.VERTICAL))
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //getRestaurants()
//    }

    override fun onStart() {
        super.onStart()

        viewModel.restaurants.observe(this, Observer {
            it?.let { restaurants ->
                restaurantAdapter.update(restaurants)
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            it?.let { error ->
                Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun itemClicked(itemId: String) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.mainFrameLayout, RestaurantFragment.newInstance(itemId))
            ?.addToBackStack(null)
            ?.commit()
    }

//    private fun getRestaurants() {
//        BasicService.instance.getRestaurants().enqueue(object: Callback<ArrayList<Restaurant>> {
//            override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
//                when (response.isSuccessful) {
//                    true -> response.body()?.let {
//                        restaurantAdapter.update(it)
//                    }
//                    false -> showError(response.code().toString())
//                }
//            }
//            override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
//                showError(t.localizedMessage)
//            }
//        })
//    }
//
//    fun showResult(title: String?) {
//        Log.d("Show result",title)
//    }
//
//    fun showError(error: String?) {
//        Log.d("Error", error)
//    }

}