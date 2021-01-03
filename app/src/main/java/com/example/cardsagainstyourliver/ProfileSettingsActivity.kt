package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profilesettings.*

class ProfileSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilesettings)

        val mIntent = intent
        val playerId = mIntent.getIntExtra("playerId", -1)
        val mode = mIntent.getStringExtra("mode")
        Log.d("intExtra", "id:" + playerId.toString() + " mode: " + mode)

        val context = this
        var db = DBHandler(context)
        var data = db.readData()

        if (mode == "edit") {//setzt die Werte der DB ein
            edit_name.setText(data.get(playerId).playerName)
            edit_age2.setText(data.get(playerId).age.toString())
            edit_size.setText(data.get(playerId).size.toString())
            edit_weight.setText(data.get(playerId).weight.toString())
            when (data.get(playerId).gender) {
                1 -> radioButton.setChecked(true)
                2 -> radioButton2.setChecked(true)
            }
            when (data.get(playerId).drink) {
                1 -> drink_1.setChecked(true)
                2 -> drink_2.setChecked(true)
                3 -> drink_3.setChecked(true)
                4 -> drink_4.setChecked(true)
                5 -> drink_5.setChecked(true)
                6 -> drink_6.setChecked(true)
                7 -> drink_7.setChecked(true)
                8 -> drink_8.setChecked(true)
                9 -> drink_9.setChecked(true)
                10 -> drink_10.setChecked(true)
                11 -> drink_11.setChecked(true)
                12 -> drink_12.setChecked(true)
            }
        }

        correct_button.setOnClickListener{
            if(mode=="add"){
                val newPlayer=PlayerClass(edit_name.getText().toString(),edit_size.getText().toString().toInt(),edit_weight.getText().toString().toInt(),edit_age2.getText().toString().toInt(),1,1,0,0,0)
                db.insertData(newPlayer)
                onClickSubmitButton()
            }
            if
        }
    }

    fun onClickSubmitButton() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }


    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        Log.d("test",edit_name.text.toString())
        startActivity(BackToMenuButton)
    }
}