package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class NewGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
    }


    fun onClickBackToGamelistButton(view: View) {
        val BackToGamelistButton = Intent(this, GamelistActivity::class.java)
        startActivity(BackToGamelistButton)
    }
}