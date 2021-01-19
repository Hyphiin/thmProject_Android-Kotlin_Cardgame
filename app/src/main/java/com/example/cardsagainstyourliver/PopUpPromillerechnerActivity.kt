package com.example.cardsagainstyourliver

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View

class PopUpPromillerechnerActivity : Activity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.promille_anzeige)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels




        window.setLayout(
            (width * 0.8).toInt(),
            (height * 0.6).toInt()
        )


    }

    fun popupClosed(view: View) {

        finish()

    }

}