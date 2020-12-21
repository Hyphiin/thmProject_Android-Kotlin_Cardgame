package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class GamelistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelist)


    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }


    fun onClickSchwimmenButton(v: View) {
        val SchwimmenButton = Intent(this, SchwimmenActivity::class.java)
        startActivity(SchwimmenButton)
    }

    fun onClickBettlerButton(view: View) {
        val BettlerButton = Intent(this, BettlerActivity::class.java)
        startActivity(BettlerButton)
    }


    fun onClickNewGameButton(view: View) {
        val BettlerNewGameButton = Intent(this, NewGameActivity::class.java)
        startActivity(BettlerNewGameButton)
    }
}