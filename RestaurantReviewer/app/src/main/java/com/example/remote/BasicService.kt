package com.example.remote

import com.example.model.Restaurant
import com.example.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface BasicService {

    companion object {
        val instance: BasicService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClientBuilder = OkHttpClient.Builder()
            okhttpClientBuilder.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurantsreviews-bstn.firebaseapp.com/api/v1/")
                .client(okhttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(BasicService::class.java)
        }
    }

    @POST("user")
    fun login(@Body user: User): Call<Unit>

    @GET("restaurants")
    fun getRestaurants(): Call<ArrayList<Restaurant>>

}