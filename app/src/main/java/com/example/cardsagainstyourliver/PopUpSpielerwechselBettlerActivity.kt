package com.example.cardsagainstyourliver

import android.app.Activity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.player_change.*

class PopUpSpielerwechselBettlerActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getStringExtra("playerName")
        val beginn = intent.getBooleanExtra("beginn", false)

        setContentView(R.layout.player_change)
        if(beginn){
            textView4.setText("beginnt!")
            test.setText("Ok")
        }
        player_name1.setText(player)
        textView2.setVisibility(View.INVISIBLE)
        heart1.setVisibility(View.INVISIBLE)
        heart2.setVisibility(View.INVISIBLE)
        heart3.setVisibility(View.INVISIBLE)

        test.setOnClickListener {
            finish()
        }
    }


}