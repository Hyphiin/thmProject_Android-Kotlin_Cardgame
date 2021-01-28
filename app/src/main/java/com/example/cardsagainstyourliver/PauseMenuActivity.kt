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

        val buttonResume: Button = findViewById(R.id.resume_button)
        buttonResume.setOnClickListener {
                finish()
        }


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
