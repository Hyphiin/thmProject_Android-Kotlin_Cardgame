package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class BettlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spielerauswahl)
    }

    fun onClickStartGame(view: View) {
        setContentView(R.layout.activity_bettler)
    }

    fun onClickPauseMenuButton(view: View) {
        val BackToPauseButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(BackToPauseButton)
    }
}