package com.example.restaurantreviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            actionBar?.title = "Login"
            supportActionBar?.title = "Login"
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFrameLayout, LoginFragment())
                .commit()
        }
    }
}
