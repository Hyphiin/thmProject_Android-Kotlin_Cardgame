package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast

class PopUpSpielendeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.spielende)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels


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