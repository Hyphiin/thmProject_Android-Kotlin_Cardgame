package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Activitywechsel Zurück via Button
        val buttonBack: ImageButton = findViewById(R.id.back_button)
        buttonBack.setOnClickListener {
            val testBack = Intent(this, MainActivity::class.java)
            startActivity(testBack)
        }
    }
}