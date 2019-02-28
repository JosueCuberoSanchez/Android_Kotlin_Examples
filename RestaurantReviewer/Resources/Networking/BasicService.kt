package com.example.leonardomadrigal.androidbasics.remote

import com.example.leonardomadrigal.androidbasics.model.Todo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface BasicService {

    companion object {
        val instance: BasicService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClientBuilder = OkHttpClient.Builder()
            okhttpClientBuilder.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okhttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(BasicService::class.java)
        }
    }

    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<Todo>
}