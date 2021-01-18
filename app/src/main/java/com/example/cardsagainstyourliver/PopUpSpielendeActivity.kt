package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast

class PopUpSpielendeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.rundenende)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        var textGewinner = intent.getStringExtra("textGewinner")
        val player1Name = intent.getStringExtra("player1name")
        val player2Name = intent.getStringExtra("player2name")
        val playerStart = intent.getStringExtra("playerStart")

        val textView: TextView = findViewById(R.id.player_name1)!!
        val textView2: TextView = findViewById(R.id.player_name2)!!


        if(textGewinner == player1Name){
            textView.setText(player1Name)
        }else if(textGewinner == player2Name){
            textView.setText(player2Name)
        }else{
            textView.setText("Keiner")
        }

        if(playerStart == player1Name) {
            textView2.setText(player2Name)
        }else{
            textView2.setText(player1Name)
        }



        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )


    }


    fun promilleAnzeige(view: View) {
        val toast = Toast.makeText(applicationContext, "Promilleanzeige", Toast.LENGTH_LONG)
        toast.show()

        val PromillePopUpEvent = Intent(this, PopUpPromillerechnerActivity::class.java)
        startActivity(PromillePopUpEvent)

    fun nextRound(view: View) {
        finish()
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