package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClickPlayButton(view: View) {
        val GamelistButton = Intent(this, GamelistActivity::class.java)
        startActivity(GamelistButton)
    }

    fun onClickProfileButton(view: View) {
        val ProfileButton = Intent(this, ProfileActivity::class.java)
        startActivity(ProfileButton)
    }


    fun onClickSpecialModeButton(view: View) {
        val buttonSpecialmode: Button = findViewById(R.id.specialmode)
        buttonSpecialmode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }


    fun onClickManualButton(view: View) {
        val ManualButton = Intent(this, ManualActivity::class.java)
        startActivity(ManualButton)
    }

    fun onClickSettingsButton(view: View) {
        val SettingsButton = Intent(this, SettingsActivity::class.java)
        startActivity(SettingsButton)
    }


}



