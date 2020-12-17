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

        //Activitywechsel zu MainActivity via Button
        val buttonGiveUp: Button = findViewById(R.id.giveUp_button)
        buttonGiveUp.setOnClickListener {
            val testGiveUp = Intent(this, MainActivity::class.java)
            startActivity(testGiveUp)
        }


        //Activitywechsel zu ManualActivity via Button
        val buttonManual: Button = findViewById(R.id.manual)
        buttonManual.setOnClickListener {
            val testManual = Intent(this, ManualActivity::class.java)
            startActivity(testManual)
        }
        //Activitywechsel zu SettingsActivity via Button
        val buttonSettings: Button = findViewById(R.id.settings)
        buttonSettings.setOnClickListener {
            val testSettings = Intent(this, SettingsActivity::class.java)
            startActivity(testSettings)
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

    fun onClickSettingsButton(view: View) {
        val SettingsButton = Intent(this, SettingsActivity::class.java)
        startActivity(SettingsButton)
    }

}
