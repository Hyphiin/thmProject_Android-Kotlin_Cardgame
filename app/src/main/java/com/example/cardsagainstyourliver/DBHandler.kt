package com.example.cardsagainstyourliver

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME="caylDB"
val TABLE_NAME="User"
val COL_ID="id"
val COL_NAME="spielerName"
val COL_ALTER="age"
val COL_GROESSE="groesse"
val COL_GEWICHT="gewicht"
val COL_GESCHLECHT="geschlecht"
val COL_GETRAENK="getraenk"
val COL_VERLORENESPIELE="verloreneSpiele"
val COL_GEWONNENESPIELE="gewonneneSpiele"
val COL_ALKOHOLLEVEL="alkoholLevel"

class DBHandler(var context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_NAME + " VARCHAR(255)," +
                COL_ALTER + " INTEGER," +
                COL_GROESSE +" INTEGER," +
                COL_GEWICHT +" INTEGER," +
                COL_GESCHLECHT+ " INTEGER," +
                COL_GETRAENK+ " INTEGER," +
                COL_VERLORENESPIELE+ " INTEGER," +
                COL_GEWONNENESPIELE+ " INTEGER,"+
                COL_ALKOHOLLEVEL+ " INTEGER)";

        db?.execSQL(createTable)
        Log.d("test","table created: "+ TABLE_NAME)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(player: PlayerClass){
        val db =this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,player.playerName)
        cv.put(COL_ALTER,player.age)
        cv.put(COL_GROESSE,player.size)
        cv.put(COL_GEWICHT,player.weight)
        cv.put(COL_GESCHLECHT,player.gender)
        cv.put(COL_GETRAENK,player.drink)
        cv.put(COL_VERLORENESPIELE,player.GamesLost)
        cv.put(COL_GEWONNENESPIELE,player.gamesWon)
        cv.put(COL_ALKOHOLLEVEL,player.alkoholLevel)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result== -1.toLong())
            Log.d("failes","failed")
        //Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Log.d("success","success")
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

    }

}