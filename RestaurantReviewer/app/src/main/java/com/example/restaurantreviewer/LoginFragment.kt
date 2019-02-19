package com.example.restaurantreviewer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.model.User
import com.example.remote.BasicService
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Button


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = getView()?.findViewById(R.id.loginButton) as Button
        loginButton.setOnClickListener {
            requestLogin()
        }
    }

    private fun requestLogin() {
        val email = email_edit_text.text.toString()
        val password = password_edit_text.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            val user = User(email, password)
            login(user)
        }

    }

    private fun login(user: User) {
        BasicService.instance.login(user).enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                when (response.isSuccessful) {
                    true -> showResult(response.code().toString())
                    false -> showError(response.code().toString())
                }
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                showError(t.localizedMessage)
            }
        })
    }

    fun showResult(title: String?) {
        Log.d("Show result",title)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.mainFrameLayout, DashboardFragment())
            ?.addToBackStack(null)
            ?.commit()
    }

    fun showError(error: String?) {
        Log.d("Error", error)
    }

}
