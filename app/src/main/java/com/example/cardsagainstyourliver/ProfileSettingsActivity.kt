package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ProfileSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilesettings)
    }

    fun onClickBackToProfileButton(view: View) {
        val BackToProfileButton = Intent(this, ProfileActivity::class.java)
        startActivity(BackToProfileButton)
    }


    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }
}