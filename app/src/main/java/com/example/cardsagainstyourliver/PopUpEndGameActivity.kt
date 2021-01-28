package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class PopUpEndGameActivity : Activity() {

    var player1Name = "Jürgen"
    var player2Name = "Dieter"
    var p1Pos = 0
    var p2Pos = 0
    var player1permille = 0
    var player2permille = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.end_game)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        var winner = intent.getStringExtra("WinnerWinner")
        player1Name = intent.getStringExtra("player1Name")!!
        player2Name = intent.getStringExtra("player2Name")!!
        p1Pos = intent.getIntExtra("p1Pos", -1)
        p2Pos = intent.getIntExtra("p2Pos", -1)
        val p1ID = intent.getIntExtra("p1ID", -1)
        val p2ID = intent.getIntExtra("p1ID", -1)
        player1permille = intent.getIntExtra("player1permille",-2)
        player2permille = intent.getIntExtra("player2permille",-2)

        val textView: TextView = findViewById(R.id.player_name)!!
        val promille_btn: Button = findViewById(R.id.promille_btn)
        val next_round_btn: Button = findViewById(R.id.next_round_btn)
        val test1: ImageView = findViewById(R.id.test1)

        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            test1.setImageResource(R.drawable.pause2)
            promille_btn.setVisibility(View.VISIBLE)

        } else {
            test1.setImageResource(R.drawable.pause)
            promille_btn.setVisibility(View.INVISIBLE)

        }
        next_round_btn.setOnClickListener {
            onClickGameStartButton(p1ID, p2ID, p1Pos, p2Pos, "Schwimmen")
        }

        textView.setText(winner)

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )
    }

    fun promilleAnzeige(view: View) {
        val toast = Toast.makeText(applicationContext, "Promilleanzeige", Toast.LENGTH_LONG)
        //toast.show()

        val intent = Intent(this, PopUpPermilleCalcActivity::class.java)
        intent.putExtra("player1Name",player1Name)
        intent.putExtra("player2Name",player2Name)
        intent.putExtra("p1Pos",p1Pos)
        intent.putExtra("p2Pos",p2Pos)
        intent.putExtra("player1permille", player1permille)
        intent.putExtra("player2permille", player2permille)
        startActivity(intent)
    }

    fun onClickGameStartButton( p1:Int, p2:Int,p1pos:Int,p2pos:Int, modus:String ) {
        val intent = Intent(this, SchwimmenActivity::class.java)
        intent.putExtra("idP1",p1)
        intent.putExtra("idP2",p2)
        intent.putExtra("idPos1",p1pos)
        intent.putExtra("idPos2",p2pos)

        startActivity(intent)
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