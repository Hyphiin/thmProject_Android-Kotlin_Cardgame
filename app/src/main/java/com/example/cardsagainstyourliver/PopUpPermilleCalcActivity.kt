package com.example.cardsagainstyourliver

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView

class PopUpPermilleCalcActivity : Activity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.promille_anzeige)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        val context = this
        var db = DBHandler(context)
        var data = db.readData()


        var p1name = intent.getStringExtra("player1Name")
        var p2name = intent.getStringExtra("player2Name")

        var p1Pos = intent.getIntExtra("p1Pos", -1)
        var p2Pos = intent.getIntExtra("p2Pos", -1)

        var p1permille = intent.getIntExtra("player1permille", -2)
        var p2permille = intent.getIntExtra("player2permille", -2)

        val permille1dummy = (p1permille.toDouble())
        val permille2dummy = (p2permille.toDouble())

        val permille1 = permille1dummy/100.0
        val permille2 = permille2dummy/100.0

        val permille1String = permille1.toString()
        val permille2String = permille2.toString()

        val player1Name: TextView = findViewById(R.id.p1)!!
        val player2Name: TextView = findViewById(R.id.p2)!!

        val player1permille: TextView = findViewById(R.id.p1permille)!!
        val player2permille: TextView = findViewById(R.id.p2permille)!!


        player1Name.setText(p1name)
        player2Name.setText(p2name)

        player1permille.setText(permille1String)
        player2permille.setText(permille2String)


        window.setLayout(
            (width * 0.8).toInt(),
            (height * 0.6).toInt()
        )


    }

    fun popupClosed(view: View) {

        finish()

    }

}