package com.example.cardsagainstyourliver

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast

class PopUpKartenauswahlActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.kartenauswahl)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels


        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )


    }


    fun newCards(view: View) {

        //Logik für Spielstart
        val toast = Toast.makeText(applicationContext, "neue Karten nehmen", Toast.LENGTH_LONG)
        toast.show()
        finish()

    }

    fun thisCards(view: View) {

        //Logik für Spielstart
        val toast = Toast.makeText(applicationContext, "Diese Karten behalten", Toast.LENGTH_LONG)
        toast.show()
        finish()

    }
}
