package com.example.cardsagainstyourliver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import kotlinx.android.synthetic.main.end_game.*

class PopUpEndGameBettlerActicity: Activity() {

    var player1Name = "Jürgen"
    var player2Name = "Dieter"
    var p1Pos = 0
    var p2Pos = 0
    var player1permille = 0
    var player2permille = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getStringExtra("playerName")
        player1Name = intent.getStringExtra("player1Name")!!
        player2Name = intent.getStringExtra("player2Name")!!
        p1Pos = intent.getIntExtra("p1Pos", -1)
        p2Pos = intent.getIntExtra("p2Pos", -1)
        val p1ID = intent.getIntExtra("p1ID", -1)
        val p2ID = intent.getIntExtra("p1ID", -1)
        player1permille = intent.getIntExtra("player1permille",-2)
        player2permille = intent.getIntExtra("player2permille",-2)

        val dm: DisplayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels

        var isKing = getString(R.string.is_king)

        setContentView(R.layout.end_game)
        player_name.setText(player)
        result_text.setText(isKing)

        window.setLayout(
            (width * 0.805).toInt(),
            (height * 0.805).toInt()
        )

        next_round_btn.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }

        menu_btn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        promille_btn.setOnClickListener{
            val intent = Intent(this, PopUpPermilleCalcActivity::class.java)
            intent.putExtra("player1Name",player1Name)
            intent.putExtra("player2Name",player2Name)
            intent.putExtra("p1Pos",p1Pos)
            intent.putExtra("p2Pos",p2Pos)
            intent.putExtra("player1permille", player1permille)
            intent.putExtra("player2permille", player2permille)
            startActivity(intent)
        }


    }
}