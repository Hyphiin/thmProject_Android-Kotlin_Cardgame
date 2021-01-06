package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class SpielerauswahlActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spielerauswahl)

        val message1 = intent.getStringExtra(EXTRA_MESSAGE_S)
        val message2 = intent.getStringExtra(EXTRA_MESSAGE_B)


    }

    fun onClickGameStartButton(view: View) {

        if (EXTRA_MESSAGE_S == "Schwimmen") {
            val GameStartButton = Intent(this, SchwimmenActivity::class.java)
            startActivity(GameStartButton)
        } else {
            val GameStartButton2 = Intent(this, BettlerActivity::class.java)
            startActivity(GameStartButton2)
        }
    }

    fun onClickBackToGameListButton(view: View) {
        val BackToGameListButton = Intent(this, GamelistActivity::class.java)
        startActivity(BackToGameListButton)
    }
}
