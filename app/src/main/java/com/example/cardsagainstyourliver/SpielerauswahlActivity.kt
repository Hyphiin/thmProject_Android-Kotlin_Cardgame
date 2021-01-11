package com.example.cardsagainstyourliver

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.name_list_item.*
import kotlinx.android.synthetic.main.profile_acivity_new.*


class SpielerauswahlActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_acivity_new)

        val modus = intent.getStringExtra("Gamemode")


        val context=this
        var db =DBHandler(context)
        var names: MutableList<String> = ArrayList()
        var ids: MutableList<Int> = ArrayList()
        var data=db.readData()
        for(i in 0..(data.size-1)){
            names.add(data.get(i).playerName)
            ids.add(data.get(i).id)
            Log.d("dbAusgabe", data.get(i).playerName+data.get(i).gender.toString()+data.get(i).drink.toString())
        }

        btn_start.setVisibility(View.VISIBLE)

        val adapter = ArrayAdapter(this,
            R.layout.name_list_item, names)

        var selected: MutableList<Int> = ArrayList()

        val nameList: ListView = findViewById(R.id.name_list)
        nameList.setAdapter(adapter)
        nameList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                val id= ids[position]
                if(selected.contains(id)){
                    selected.remove(id)
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#007D7C"))//Wie kriege ich hier Backgroundcolor2?
                }
                else{
                    selected.add(id)
                    parent.getChildAt(position).setBackgroundColor(Color.GRAY)
                }
            }
        }

        btn_start.setOnClickListener{
            if(selected.size==2){
                Log.d("jo","check")
                val p1=selected[0]
                val p2=selected[1]
                onClickGameStartButton(p1,p2,modus)
            }
        }


    }

    fun onClickGameStartButton( p1:Int, p2:Int, modus:String ) {

        if (modus== EXTRA_MESSAGE_S) {
            val intent = Intent(this, SchwimmenActivity::class.java)
            intent.putExtra("idP1",p1)
            intent.putExtra("idP2",p2)
            startActivity(intent)
        } else {
            val intent = Intent(this, BettlerActivity::class.java)
            intent.putExtra("idP1",p1)
            intent.putExtra("idP2",p2)
            startActivity(intent)
        }
    }

    fun onClickBackToGameListButton(view: View) {
        val BackToGameListButton = Intent(this, GamelistActivity::class.java)
        startActivity(BackToGameListButton)
    }
}
