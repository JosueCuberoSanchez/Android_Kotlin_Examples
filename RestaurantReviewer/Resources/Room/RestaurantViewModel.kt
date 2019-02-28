package io.brainstation.basics.view.restaurants

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import io.brainstation.basics.application.VenueDatabase
import io.brainstation.basics.model.Restaurant
import io.brainstation.basics.model.Review
import io.brainstation.basics.remote.BasicsService
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantViewModel(
    private val venueDatabase: VenueDatabase,
    private val basicsService: BasicsService
) : ViewModel() {
    val errors = MutableLiveData<Throwable>()

    var title: LiveData<String>? = null
    var rating: LiveData<String>? = null
    var headerImageUrl: LiveData<String>? = null
    var reviews: LiveData<List<Review>>? = null
    var showEmptyText: LiveData<Boolean>? = null
    var isLoading = MutableLiveData<Boolean>()

    fun init(resId: String) {

        isLoading.postValue(true)
        basicsService.getRestaurant(resId)
            .enqueue(object : Callback<Restaurant> {
                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    errors.postValue(t)
                    isLoading.postValue(false)
                }

                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    if (response.isSuccessful) {
                        response.body()?.let { restaurant ->
                            doAsync {
                                venueDatabase.getRestaurantDao().insert(restaurant)
                                isLoading.postValue(false)
                            }
                        }
                    }
                }
            })

        val restaurant = venueDatabase.getRestaurantDao().getRestaurant(resId)

        title = Transformations.map(restaurant) { res ->
            res.name
        }

        rating = Transformations.map(restaurant) { res ->
            res.overallRating
        }

        headerImageUrl = Transformations.map(restaurant) { res ->
            res.location.photos.firstOrNull()?.url
        }

        reviews = Transformations.map(restaurant) { res ->
            res.reviews.toList()
        }

        reviews?.let {
            showEmptyText = Transformations.map(it) {reviews ->
                reviews.isEmpty()
            }
        }
    }

}