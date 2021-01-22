package com.example.cardsagainstyourliver

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast

class PopUpPromillerechnerActivity : Activity() {
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

        //val p1permille = data.get(p1Pos).alcoholLevel
        //val p2permille = data.get(p2Pos).alcoholLevel

        val permille1zwischen = (p1permille.toDouble())
        val permille2zwischen = (p2permille.toDouble())

        val permille1 = permille1zwischen/100.0
        val permille2 = permille2zwischen/100.0

        val permille1String = permille1.toString()
        val permille2String = permille2.toString()

        val player1Name: TextView = findViewById(R.id.p1)!!
        val player2Name: TextView = findViewById(R.id.p2)!!

        val player1permille: TextView = findViewById(R.id.p1permille)!!
        val player2permille: TextView = findViewById(R.id.p2permille)!!


        val toast = Toast.makeText(applicationContext, "$p1name,$p2name,$permille1,$permille2", Toast.LENGTH_LONG)
        //toast.show()


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