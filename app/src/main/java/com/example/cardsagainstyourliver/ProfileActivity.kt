package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.profile_acivity_new.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_acivity_new)//eigentlich: activity_profile

        val context = this
        var db =DBHandler(context)
        var data=db.readData()
        for(i in 0..(data.size-1)){
            tv_Result.append(data.get(i).playerName)
        }
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