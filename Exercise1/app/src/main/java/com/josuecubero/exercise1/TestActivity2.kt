package com.josuecubero.exercise1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        sendButton.setOnClickListener {
            val intent = Intent(this, TestActivity1::class.java)
            val receivedText = findViewById<EditText>(R.id.editText).text.toString()
            intent.putExtra("RECEIVED_TEXT", receivedText)
            startActivity(intent)
        }
    }
}
