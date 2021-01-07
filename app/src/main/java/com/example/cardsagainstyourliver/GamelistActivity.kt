package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE_S = "Schwimmen"
const val EXTRA_MESSAGE_B = "Bettler"


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

        val SchwimmenButton = Intent(this, SpielerauswahlActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE_S, 0)
        }
        startActivity(SchwimmenButton)
    }

    fun onClickBettlerButton(view: View) {


        val BettlerButton =
            Intent(this, SpielerauswahlActivity::class.java).apply { putExtra(EXTRA_MESSAGE_B, 0) }
        startActivity(BettlerButton)
    }


    fun onClickNewGameButton(view: View) {
        val BettlerNewGameButton = Intent(this, NewGameActivity::class.java)
        startActivity(BettlerNewGameButton)
    }
}