package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.profile_acivity_new.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_acivity_new)//eigentlich: activity_profile

        val context = this//hier werden die namen aus der DB geholt und angezeigt kann auch auf beliebige andere View elemente angewendet werden...
        var db =DBHandler(context)
        var names: MutableList<String> = ArrayList()
        var data=db.readData()
        for(i in 0..(data.size-1)){
            names.add(data.get(i).playerName)
        }

        val adapter = ArrayAdapter(this,
            R.layout.name_list_item, names) //namelistitem für design einzelner einträge

        val nameList: ListView = findViewById(R.id.name_list)
        nameList.setAdapter(adapter)

        nameList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {


                val itemValue = nameList.getItemAtPosition(position) as String

                // Toast the values
                Toast.makeText(applicationContext,
                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
                    .show()
            }
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