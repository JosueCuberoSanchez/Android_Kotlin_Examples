package com.example.leonardomadrigal.androidbasics.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.leonardomadrigal.androidbasics.R
import kotlinx.android.synthetic.main.activity_test1.*

class TestActivity1 : AppCompatActivity() {

    val TEXT_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
    }

    fun launchSecondActivity(view: View) {
        val intent = Intent(this, TestActivity2::class.java)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            textContainer.text = data?.getStringExtra(TestActivity2.EXTRA_REPLY)
        }
    }
}
