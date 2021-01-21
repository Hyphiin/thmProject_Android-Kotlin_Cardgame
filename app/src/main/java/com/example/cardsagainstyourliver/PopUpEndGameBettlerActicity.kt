package com.example.cardsagainstyourliver

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.spielende.*

class PopUpEndGameBettlerActicity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getStringExtra("playerName")

        setContentView(R.layout.spielende)
        player_name.setText(player)
        result_text.setText("ist König!")

        next_round_btn.setOnClickListener {
            finish()
        }


    }
}