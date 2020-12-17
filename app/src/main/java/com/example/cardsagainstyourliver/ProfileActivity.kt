package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

    fun onClickNewProfil(view: View) {
        val newProfil = Intent(this, ProfileSettingsActivity::class.java)
        startActivity(newProfil)
    }

    fun onClickResumeButton(view: View) {
        val ResumeButton = Intent(this, SchwimmenActivity::class.java)
        startActivity(ResumeButton)
    }

    fun onClickProfileEditButton(view: View) {
        val ProfileEditButton = Intent(this, ProfileSettingsActivity::class.java)
        startActivity(ProfileEditButton)
    }
}