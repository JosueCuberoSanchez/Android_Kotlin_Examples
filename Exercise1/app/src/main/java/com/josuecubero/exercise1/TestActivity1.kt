package com.josuecubero.exercise1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test1.*

class TestActivity1 : AppCompatActivity() {

    private val TEXT_REQUEST = 1
    private var received_text: String? = "Nothing received"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        if(savedInstanceState!=null)
        {
            received_text = savedInstanceState.getString("received_text")
        } else {
            received_text = "Nothing received"
        }
        textContainer.text = received_text

        button.setOnClickListener {
            val intent = Intent(this, TestActivity2::class.java)
            startActivityForResult(intent, TEXT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST && resultCode == Activity.RESULT_OK) {
            received_text = data?.getStringExtra(TestActivity2.EXTRA_REPLY)
            textContainer.text = data?.getStringExtra(TestActivity2.EXTRA_REPLY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("received_text", received_text)
    }
}
