package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.spielende.*

class PopUpEndGameBettlerActicity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getStringExtra("playerName")

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels

        setContentView(R.layout.spielende)
        player_name.setText(player)
        result_text.setText("ist König!")

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )

        next_round_btn.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }


    }
}