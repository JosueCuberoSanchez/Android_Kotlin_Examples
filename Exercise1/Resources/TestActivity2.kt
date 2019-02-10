package com.example.leonardomadrigal.androidbasics.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.leonardomadrigal.androidbasics.R
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity2 : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = "REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    fun sendResult(view: View) {
        editText.text?.let {
            val intent = Intent()
            intent.putExtra(EXTRA_REPLY, it.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
