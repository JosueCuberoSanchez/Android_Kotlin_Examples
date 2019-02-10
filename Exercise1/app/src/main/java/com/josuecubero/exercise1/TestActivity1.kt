package com.josuecubero.exercise1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test1.*

class TestActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        var receivedText = intent.getStringExtra("RECEIVED_TEXT")
        if (receivedText == null) {
            receivedText = "Nothing received"
        }
        val textView: TextView = findViewById(R.id.textContainer)
        textView.text = receivedText


        button.setOnClickListener {
            val intent = Intent(this, TestActivity2::class.java)
            startActivity(intent)
        }
    }
}
