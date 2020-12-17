package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SchwimmenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schwimmen)


    }

    fun onClickPauseMenuButton(view: View) {
        val BackToPauseButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(BackToPauseButton)
    }

}