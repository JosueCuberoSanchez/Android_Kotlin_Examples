package com.example.leonardomadrigal.androidbasics.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.leonardomadrigal.androidbasics.R

import kotlinx.android.synthetic.main.activity_main.* // Kotlin extensions

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null

    // Lifecycle events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.mainTextView) as? TextView
        textView?.let {
            it.text = "Hola Mundo"
        }

        simpleButton.setOnClickListener {
            val intent = Intent(this, TestActivity3::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
