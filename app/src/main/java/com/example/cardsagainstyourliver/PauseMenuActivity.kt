package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PauseMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pausemenu)


        //Activitywechsel zu Schwimmen/Bettler Activity via Button
        val buttonResume: Button = findViewById(R.id.resume_button)
        buttonResume.setOnClickListener {
            val testResume = Intent(this, SchwimmenActivity::class.java)
            startActivity(testResume)
        }


    }

    fun onClickResumeButton(view: View) {
        val ResumeButton = Intent(this, SchwimmenActivity::class.java)
        startActivity(ResumeButton)
    }

    fun onClickGiveUpButton(view: View) {
        val GiveUpButton = Intent(this, MainActivity::class.java)
        startActivity(GiveUpButton)
    }

    fun onClickManualButton(view: View) {
        val ManualButton = Intent(this, ManualActivity::class.java)
        startActivity(ManualButton)
    }


}
