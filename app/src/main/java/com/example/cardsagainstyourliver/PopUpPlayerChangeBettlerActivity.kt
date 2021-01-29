package com.example.cardsagainstyourliver

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import kotlinx.android.synthetic.main.player_change.*

class PopUpPlayerChangeBettlerActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getStringExtra("playerName")
        val beginn = intent.getBooleanExtra("beginn", false)
        val ende = intent.getBooleanExtra("ende", false)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels

        setContentView(R.layout.player_change)
        if(beginn){
            var begins = getString(R.string.begins)
            textView4.setText("$begins")
            test.setText("Ok")
        }
        if(ende){
            var giveCard = getString(R.string.give_card)
            textView4.setText("$giveCard")
            test.setText("Ok")
        }
        player_name1.setText(player)
        textView2.setVisibility(View.INVISIBLE)
        heart1.setVisibility(View.INVISIBLE)
        heart2.setVisibility(View.INVISIBLE)
        heart3.setVisibility(View.INVISIBLE)

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )

        test.setOnClickListener {
            finish()
        }
    }


}