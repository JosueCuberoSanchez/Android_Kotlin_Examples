package com.example.leonardomadrigal.androidbasics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.leonardomadrigal.androidbasics.model.Todo
import com.example.leonardomadrigal.androidbasics.remote.BasicService
import kotlinx.android.synthetic.main.activity_request.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)
    }

    fun request(view: View) {
        val todoId = input.text.toString()
        loading.visibility = View.VISIBLE

        if (!TextUtils.isEmpty(todoId)) {
            getTodo(todoId.toInt())
        } else {
            getTodos()
        }

    }

    private fun getTodos() {
        doAsync {
            BasicService.instance.getTodos().enqueue(object: Callback<List<Todo>> {
                override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                    uiThread {
                        when (response.isSuccessful) {
                            true -> showResult("Results: ${response.body()?.size}")
                            false -> showError()
                        }
                    }
                }

                override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                    uiThread {
                        showError()
                    }
                }
            })
        }
    }

    private fun getTodo(id: Int) {
        doAsync {
            BasicService.instance.getTodo(id).enqueue(object: Callback<Todo> {
                override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                    uiThread {
                        when (response.isSuccessful) {
                            true -> showResult(response.body()?.title)
                            false -> showError()
                        }
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    uiThread {
                        showError()
                    }
                }
            })
        }
    }

    fun showResult(title: String?) {
        loading.visibility = View.GONE
        textView.text = title
    }

    fun showError() {
        textView.text = "Error"
    }
}
