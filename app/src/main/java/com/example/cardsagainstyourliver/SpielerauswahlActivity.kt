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
        var data = db.readData()
        for (i in 0..(data.size - 1)) {
            names.add(data.get(i).playerName)
            ids.add(data.get(i).id)
            Log.d(
                "dbAusgabe",
                data.get(i).playerName + data.get(i).gender.toString() + data.get(i).drink.toString()
            )
        }

        profil_text2.setText("Spielerauswahl")
        btn_start.setVisibility(View.VISIBLE)
        btn_add.setVisibility(View.INVISIBLE)


        val adapter = ArrayAdapter(
            this,
            R.layout.name_list_item, names
        )

        var selected: MutableList<Int> = ArrayList()
        var selectedpos: MutableList<Int> = ArrayList()

        val nameList: ListView = findViewById(R.id.name_list)
        nameList.setAdapter(adapter)
        nameList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                val id= ids[position]

                if(selected.contains(id)){
                    selected.remove(id)
                    selectedpos.remove(position)
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#007D7C"))//Wie kriege ich hier Backgroundcolor2?
                }
                else{
                    selected.add(id)
                    selectedpos.add(position)
                    parent.getChildAt(position).setBackgroundColor(Color.GRAY)
                }
            }
        }

        btn_start.setOnClickListener{
            if(selected.size==2){
                Log.d("jo","check")
                val p1=selected[0]
                val p2=selected[1]
                val p1pos=selectedpos[0]
                val p2pos=selectedpos[1]
                Log.d("jo",p1pos.toString()+" "+p2pos.toString())

                onClickGameStartButton(p1,p2,p1pos,p2pos,modus)
            }
        }

        back_button.setOnClickListener {
            val BackToMenuButton = Intent(this, MainActivity::class.java)
            this.startActivity(BackToMenuButton)
        }


    }

    fun onClickGameStartButton( p1:Int, p2:Int,p1pos:Int,p2pos:Int, modus:String ) {

        if (modus== EXTRA_MESSAGE_S) {
            val intent = Intent(this, SchwimmenActivity::class.java)
            intent.putExtra("idP1",p1)
            intent.putExtra("idP2",p2)
            intent.putExtra("idPos1",p1pos)
            intent.putExtra("idPos2",p2pos)

            startActivity(intent)
        } else {
            val intent = Intent(this, BettlerActivity::class.java)
            intent.putExtra("idP1",p1)
            intent.putExtra("idP2",p2)
            intent.putExtra("idPos1",p1pos)
            intent.putExtra("idPos2",p2pos)
            startActivity(intent)
        }
    }

    fun onClickBackToGameListButton(view: View) {
        val BackToGameListButton = Intent(this, GamelistActivity::class.java)
        startActivity(BackToGameListButton)
    }
}

