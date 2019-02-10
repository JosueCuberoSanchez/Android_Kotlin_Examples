package com.example.leonardomadrigal.androidbasics.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.leonardomadrigal.androidbasics.R
import fragments.DummyFragment

class TestActivity4 : AppCompatActivity(), DummyFragment.OnMessageSentListener {

    companion object {
        val EXTRA_REPLY = "REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test4)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, DummyFragment.newInstance(), "rageComicList")
                .commit()
        }
    }

    override fun onMessageSent(message: String) {
        val intent = Intent()
        intent.putExtra(TestActivity2.EXTRA_REPLY, message)
        setResult(RESULT_OK, intent)
        finish()
    }
}
