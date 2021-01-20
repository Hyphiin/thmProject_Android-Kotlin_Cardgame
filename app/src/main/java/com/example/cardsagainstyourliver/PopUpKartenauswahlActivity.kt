package com.example.cardsagainstyourliver

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class PopUpKartenauswahlActivity : Activity() {

    var p1Pos = 0
    var p2Pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.kartenauswahl)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        val card1: ImageView = findViewById(R.id.table_card_01)!!
        val card2: ImageView = findViewById(R.id.table_card_02)!!
        val card3: ImageView = findViewById(R.id.table_card_03)!!
        val textView: TextView = findViewById(R.id.player_nameWahl)!!

        val p1hand = intent.getIntExtra("p1hand", -1)
        val p1hand2 = intent.getIntExtra("p1hand2", -1)
        val p1hand3 = intent.getIntExtra("p1hand3", -1)

        var name = intent.getStringExtra("name")
        val player1Name = intent.getStringExtra("player1name")
        val player2Name = intent.getStringExtra("player2name")

        p1Pos = intent.getIntExtra("idPos1", -1)
        p2Pos= intent.getIntExtra("idPos1", -1)

        if(name == player1Name){
            textView.setText(player2Name)
        }else {
            textView.setText(player1Name)
        }

        card1.setImageDrawable(getDrawable(p1hand))
        card2.setImageDrawable(getDrawable(p1hand2))
        card3.setImageDrawable(getDrawable(p1hand3))


        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )


    }



    fun newCards(view: View) {
        val toast = Toast.makeText(applicationContext, "neue Karten nehmen", Toast.LENGTH_LONG)
        toast.show()

        val intent = Intent()
        intent.putExtra("idPos1", p1Pos)
        intent.putExtra("idPos2", p2Pos)
        setResult(RESULT_OK, intent)
        finish()

    }

    fun thisCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Diese Karten behalten", Toast.LENGTH_LONG)
        toast.show()

        val intent = Intent()
        intent.putExtra("idPos1", p1Pos)
        intent.putExtra("idPos2", p2Pos)
        setResult(RESULT_CANCELED, intent)
        finish()

    }
}
