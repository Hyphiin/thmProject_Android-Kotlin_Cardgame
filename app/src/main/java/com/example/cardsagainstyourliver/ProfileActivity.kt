package com.example.cardsagainstyourliver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_acivity_new)//eigentlich: activity_profile

        val context = this//hier werden die namen aus der DB geholt und angezeigt kann auch auf beliebige andere View elemente angewendet werden...
        var db =DBHandler(context)
        var names: MutableList<String> = ArrayList()
        var ids: MutableList<Int> = ArrayList()
        var data=db.readData()
        for(i in 0..(data.size-1)){
            names.add(data.get(i).playerName)
            ids.add(data.get(i).id)
            Log.d("dbAusgabe", data.get(i).playerName+data.get(i).gender.toString()+data.get(i).drink.toString())
        }

        val adapter = ArrayAdapter(this,
            R.layout.name_list_item, names) //name_list_item für design einzelner einträge

        val nameList: ListView = findViewById(R.id.name_list)
        nameList.setAdapter(adapter)

        nameList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                val id= ids[position]
                goToProfile(position, id)
            }
        }


    }


    fun goToProfile(playerPosition:Int, playerId:Int){
        val intent = Intent(this, ProfileSettingsActivity::class.java)
        intent.putExtra("playerId",playerId)
        intent.putExtra("playerPosition",playerPosition)

        intent.putExtra("mode", "edit")
        startActivity(intent)
    }

    fun onClickBackToMenuButton(view: View) {
        val BackToMenuButton = Intent(this, MainActivity::class.java)
        startActivity(BackToMenuButton)
    }

    fun onClickNewProfil(view: View) {
        val intent = Intent(this, ProfileSettingsActivity::class.java)
        intent.putExtra("mode", "add")
        startActivity(intent)
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