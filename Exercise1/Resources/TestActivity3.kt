package com.example.leonardomadrigal.androidbasics.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.leonardomadrigal.androidbasics.R
import fragments.DummyFragment
import kotlinx.android.synthetic.main.activity_test3.*

class TestActivity3 : AppCompatActivity(), DummyFragment.OnMessageSentListener {

    val TEXT_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)
    }

    override fun onMessageSent(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun launchSecondActivity(view: View) {
        val intent = Intent(this, TestActivity4::class.java)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST &&
            resultCode == Activity.RESULT_OK) {
            textContainer.text = data?.getStringExtra(TestActivity4.EXTRA_REPLY)
        }
    }
}
