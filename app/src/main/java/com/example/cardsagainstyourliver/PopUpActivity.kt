package com.example.cardsagainstyourliver

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View

class PopUpActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.player_change)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels


        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )


    }


    fun nextPlayer(view: View) {

        finish()

    }
}