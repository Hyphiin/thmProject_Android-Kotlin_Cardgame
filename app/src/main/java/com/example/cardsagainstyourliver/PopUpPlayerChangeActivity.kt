package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class PopUpPlayerChangeActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.player_change)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        val player1heart = intent.getIntExtra("heart1", -1)
        val player2heart = intent.getIntExtra("heart2", -1)
        var name = intent.getStringExtra("name")
        val player1Name = intent.getStringExtra("player1name")
        val player2Name = intent.getStringExtra("player2name")
        val knock = intent.getBooleanExtra("knock", false)
        val shove = intent.getBooleanExtra("shove", false)

        val heart1: ImageView = findViewById(R.id.heart1)!!
        val heart2: ImageView = findViewById(R.id.heart2)!!
        val heart3: ImageView = findViewById(R.id.heart3)!!
        val textView: TextView = findViewById(R.id.player_name1)!!
        val textView3: TextView = findViewById(R.id.coole_View)!!

        val test1: ImageView = findViewById(R.id.test1)

        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            test1.setImageResource(R.drawable.pause2)
        } else {
            test1.setImageResource(R.drawable.pause)
        }

        if (name == player1Name) {
            textView.setText(player2Name)
            if (knock) {
                val schwimmeHasEnded = getString(R.string.schwimmen_has_ended)
                textView3.setText("$player1Name $schwimmeHasEnded")
            }
            if (shove) {
                val schwimmenHasClosed = getString(R.string.schwimmen_has_closed)
                textView3.setText("$player1Name $schwimmenHasClosed")
            }
            if (player2heart == 2) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
            } else if (player2heart == 1) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart2.setImageDrawable(getDrawable(R.drawable.heart_empty))
            } else if (player2heart == 0) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart2.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart1.setImageDrawable(getDrawable(R.drawable.heart_empty))
            }
        }else {
            textView.setText(player1Name)
            if(knock){
                val schwimmeHasEnded = getString(R.string.schwimmen_has_ended)
                textView3.setText("$player2Name $schwimmeHasEnded")
            }
            if(shove){
                val schwimmenHasClosed = getString(R.string.schwimmen_has_closed)
                textView3.setText("$player2Name $schwimmenHasClosed")
            }
            if (player1heart == 2) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
            } else if (player1heart == 1) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart2.setImageDrawable(getDrawable(R.drawable.heart_empty))
            } else if (player1heart == 0) {
                heart3.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart2.setImageDrawable(getDrawable(R.drawable.heart_empty))
                heart1.setImageDrawable(getDrawable(R.drawable.heart_empty))
            }
        }

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )
}


    fun nextPlayer(view: View) {

        finish()

    }
}
