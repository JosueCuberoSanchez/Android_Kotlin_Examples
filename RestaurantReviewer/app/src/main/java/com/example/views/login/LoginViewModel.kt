package com.example.views.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.model.User
import com.example.remote.BasicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val service: BasicService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(service) as T
    }
}

class LoginViewModel(service: BasicService) : ViewModel() {

    val service: BasicService
    val errorMessage = MutableLiveData<String>()
    val authenticated = MutableLiveData<Boolean>()

    init { this.service = service }

    fun authenticate(user: User) {
        authenticated.postValue(false)
        service.login(user).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    authenticated.postValue(true)
                }
            }
        })
    }

}
