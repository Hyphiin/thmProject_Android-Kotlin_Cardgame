package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SpielerauswahlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spielerauswahl)


    }

    fun onClickGameStartButton(view: View) {
        val GameStartButton = Intent(this, SchwimmenActivity::class.java)
        startActivity(GameStartButton)
    }
}