package com.test.skytest.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.skytest.R
import com.test.skytest.screen.search.SearchFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, SearchFragment())
                .commit()
        }
    }
}