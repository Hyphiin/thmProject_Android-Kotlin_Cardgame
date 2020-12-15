package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Activitywechsel zu GameListActivity via Button
        val buttonPlay: Button = findViewById(R.id.play)
        buttonPlay.setOnClickListener {
            val testGamelist = Intent(this, GamelistActivity::class.java)
            startActivity(testGamelist)
        }

        val buttonSpecialmode: Button = findViewById(R.id.specialmode)
        buttonSpecialmode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        //Activitywechsel zu ProfileActivity via Button
        val buttonProfile: Button = findViewById(R.id.profile)
        buttonProfile.setOnClickListener {
            val testProfile = Intent(this, ProfileActivity::class.java)
            startActivity(testProfile)
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

}



