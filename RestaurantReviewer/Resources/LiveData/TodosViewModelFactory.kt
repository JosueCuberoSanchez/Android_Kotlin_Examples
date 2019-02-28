package com.example.leonardomadrigal.androidbasics.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.leonardomadrigal.androidbasics.remote.BasicService

@Suppress("UNCHECKED_CAST")
class TodosViewModelFactory(private val service: BasicService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodosViewModel(service) as T
    }
}

