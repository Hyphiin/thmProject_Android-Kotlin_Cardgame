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
val COL_NAME="playerName"
val COL_AGE="age"
val COL_SIZE="size"
val COL_WEIGHT="weight"
val COL_GENDER="gender"
val COl_DRINK="drink"
val COL_LOSTGAMES="lostGames"
val COL_WONGAMES="wonGames"
val COL_ALCOHOLLEVEL="alcoholLevel"

class DBHandler(var context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_NAME + " VARCHAR(255)," +
                COL_AGE + " INTEGER," +
                COL_SIZE +" INTEGER," +
                COL_WEIGHT +" INTEGER," +
                COL_GENDER+ " INTEGER," +
                COl_DRINK+ " INTEGER," +
                COL_LOSTGAMES+ " INTEGER," +
                COL_WONGAMES+ " INTEGER,"+
                COL_ALCOHOLLEVEL+ " INTEGER)";

        db?.execSQL(createTable)
        Log.d("test","table created: "+ TABLE_NAME)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(player: PlayerClass){
        val db =this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,player.playerName)
        cv.put(COL_AGE,player.age)
        cv.put(COL_SIZE,player.size)
        cv.put(COL_WEIGHT,player.weight)
        cv.put(COL_GENDER,player.gender)
        cv.put(COl_DRINK,player.drink)
        cv.put(COL_LOSTGAMES,player.gamesLost)
        cv.put(COL_WONGAMES,player.gamesWon)
        cv.put(COL_ALCOHOLLEVEL,player.alcoholLevel)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result== -1.toLong())
            Log.d("failes","failed")
        //Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Log.d("success","success")
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }
    fun readData() : MutableList<PlayerClass>{
        var list: MutableList<PlayerClass> = ArrayList()
        val db= this.readableDatabase
        val query= "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                var player=PlayerClass()
                player.id = result.getString(0).toInt()
                player.playerName = result.getString(1)
                player.age = result.getString(2).toInt()
                player.size = result.getString(3).toInt()
                player.weight = result.getString(4).toInt()
                player.gender = result.getString(5).toInt()
                player.drink = result.getString(6).toInt()
                player.gamesLost = result.getString(7).toInt()
                player.gamesWon = result.getString(8).toInt()
                player.alcoholLevel = result.getString(9).toInt()
                list.add(player)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun updateData(id:Int, name:String, age:Int, size:Int, weight:Int, gender:Int, drink:Int ){
        val db= this.writableDatabase
        val query= "Select * from " + TABLE_NAME + " where id="+id
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_NAME,name)
                cv.put(COL_AGE,age)
                cv.put(COL_SIZE,size)
                cv.put(COL_WEIGHT,weight)
                cv.put(COL_GENDER,gender)
                cv.put(COl_DRINK,drink)

                db.update(TABLE_NAME, cv, COL_ID+ "=? AND "+COL_NAME+ "=?",
                arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                        result.getString(result.getColumnIndex(COL_NAME))))
            }while(result.moveToNext())
        }
        result.close()
        db.close()
    }

    fun deleteData(id:Int){
        val db=this.writableDatabase
        db.delete(TABLE_NAME, COL_ID+"=?",arrayOf(id.toString()))
        db.close()
    }
}