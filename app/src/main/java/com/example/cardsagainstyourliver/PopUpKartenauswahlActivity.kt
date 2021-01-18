package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class PopUpKartenauswahlActivity : Activity() {

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

        val p1hand = intent.getIntExtra("p1hand",-1)
        val p1hand2 = intent.getIntExtra("p1hand2",-1)
        val p1hand3 = intent.getIntExtra("p1hand3",-1)


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
        val a = false
        //val intent = Intent(this, SchwimmenActivity::class.java)
        //intent.putExtra("a", a)
        //startActivity(intent)
        finish()

    }

    fun thisCards(view: View) {
        val toast = Toast.makeText(applicationContext, "Diese Karten behalten", Toast.LENGTH_LONG)
        toast.show()
        val a = true
        //val intent = Intent(this, SchwimmenActivity::class.java)
        //intent.putExtra("a", a)
        //startActivity(intent)
        finish()

    }
}
