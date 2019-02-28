package com.example.leonardomadrigal.androidbasics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.leonardomadrigal.androidbasics.model.Todo
import com.example.leonardomadrigal.androidbasics.remote.BasicService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val adapter = TodosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler view data binding setup
        todosList.adapter = adapter
        todosList.layoutManager = GridLayoutManager(this, this.resources.getInteger(R.integer.grid_column_count))
        todosList.addItemDecoration(DividerItemDecoration(this, GridLayoutManager.VERTICAL))
    }

    fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        loading.visibility = View.VISIBLE
        BasicService.instance.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                loading.visibility = View.GONE
                showErrorMessage(t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.update(it)
                    }
                }
                loading.visibility = View.GONE
            }
        })
    }
}
