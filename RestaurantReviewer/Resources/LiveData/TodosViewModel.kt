package com.example.leonardomadrigal.androidbasics.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.leonardomadrigal.androidbasics.model.Todo
import com.example.leonardomadrigal.androidbasics.remote.BasicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosViewModel(service: BasicService) : ViewModel() {

    val todos = MutableLiveData<List<Todo>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.postValue(true)
        service.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        todos.postValue(it)
                    }
                }
                isLoading.postValue(false)
            }
        })
    }

}