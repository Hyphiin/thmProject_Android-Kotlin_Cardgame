package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profilesettings.*

class ProfileSettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilesettings)

        val mIntent = intent
        val playerId = mIntent.getIntExtra("playerId", -1)
        val playerPosition = mIntent.getIntExtra("playerPosition", -1)
        val mode = mIntent.getStringExtra("mode")
        Log.d("intExtra", "id:" + playerId.toString() + " mode: " + mode + " position: "+ playerPosition.toString())

        val context = this
        var db = DBHandler(context)
        var data = db.readData()

        if (mode=="add"){

            delete_button.setVisibility(View.GONE)
            Log.d("layout", "invisible")
        }

        if (mode == "edit") {//setzt die Werte der DB ein
            edit_name.setText(data.get(playerPosition).playerName)
            edit_age2.setText(data.get(playerPosition).age.toString())
            edit_size.setText(data.get(playerPosition).size.toString())
            edit_weight.setText(data.get(playerPosition).weight.toString())
            val drink=radioGroup1.getChildAt(data.get(playerPosition).drink)
            val gender=gender_rbtn_group.getChildAt(data.get(playerPosition).gender)
            radioGroup1.check(drink.getId())
            gender_rbtn_group.check(gender.getId())


        }

        correct_button.setOnClickListener{//schreibt änderung oder neuen player in DB

            if(edit_name.getText().toString().isEmpty()||edit_size.getText().toString().isEmpty()||edit_weight.getText().toString().isEmpty()||edit_age2.getText().toString().isEmpty()||gender_rbtn_group.getCheckedRadioButtonId()==-1||radioGroup1.getCheckedRadioButtonId()==-1){
                Toast.makeText(this, "Bitte alles ausfüllen", Toast.LENGTH_SHORT).show()
            }
            else {
                val radioGenderID: Int = gender_rbtn_group.getCheckedRadioButtonId()
                val radioGender: View = gender_rbtn_group.findViewById(radioGenderID)
                Log.d("test","auswahl gender id "+radioGenderID)
                val idGender: Int = gender_rbtn_group.indexOfChild(radioGender)
                val radioDrinkID: Int = radioGroup1.getCheckedRadioButtonId()
                val radioDrink: View = radioGroup1.findViewById(radioDrinkID)
                val idDrink: Int = radioGroup1.indexOfChild(radioDrink)
                if (mode == "add") {
                    val newPlayer = PlayerClass(
                        edit_name.getText().toString(),
                        edit_size.getText().toString().toInt(),
                        edit_weight.getText().toString().toInt(),
                        edit_age2.getText().toString().toInt(),
                        idGender,
                        idDrink,
                        0,
                        0,
                        0
                    )
                    db.insertData(newPlayer)
                    onClickSubmitButton()
                }
                if (mode == "edit") {
                    db.updateData(
                        playerId,
                        edit_name.getText().toString(),
                        edit_age2.getText().toString().toInt(),
                        edit_size.getText().toString().toInt(),
                        edit_weight.getText().toString().toInt(),
                        idGender,
                        idDrink
                    )
                    onClickSubmitButton()
                }
            }
        }

        delete_button.setOnClickListener{//Löscht eintrag
            db.deleteData(playerId)
            onClickSubmitButton()
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


