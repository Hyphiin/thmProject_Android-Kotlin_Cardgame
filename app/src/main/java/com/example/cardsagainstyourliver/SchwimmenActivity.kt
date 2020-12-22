package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SchwimmenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spielerauswahl)



    }


    fun onClickStartGame(view: View) {
        setContentView(R.layout.activity_schwimmen)
    }

    fun onClickPauseMenuButton(view: View) {
        val PauseMenuButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(PauseMenuButton)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }


}