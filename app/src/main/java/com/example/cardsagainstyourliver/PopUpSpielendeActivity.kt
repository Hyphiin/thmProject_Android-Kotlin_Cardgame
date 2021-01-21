package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast

class PopUpSpielendeActivity : Activity() {

    var player1Name = "Jürgen"
    var player2Name = "Dieter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.spielende)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        var winner = intent.getStringExtra("WinnerWinner")
        player1Name = intent.getStringExtra("player1Name")
        player2Name = intent.getStringExtra("player2Name")

        val textView: TextView = findViewById(R.id.player_name)!!

        textView.setText(winner)

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )


    }

    fun promilleAnzeige(view: View) {
        val toast = Toast.makeText(applicationContext, "Promilleanzeige", Toast.LENGTH_LONG)
        toast.show()

        val PromillePopUpEvent = Intent(this, PopUpPromillerechnerActivity::class.java)
        intent.putExtra("player1Name",player1Name)
        intent.putExtra("player2Name",player2Name)
        startActivity(PromillePopUpEvent)
    }

    fun onClickPauseMenuButton(view: View) {
        val PauseMenuButton = Intent(this, PauseMenuActivity::class.java)
        startActivity(PauseMenuButton)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

    fun nextRound(view: View) {

        finish()

    }
}