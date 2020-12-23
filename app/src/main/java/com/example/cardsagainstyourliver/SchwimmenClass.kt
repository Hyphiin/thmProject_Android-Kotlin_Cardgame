package com.example.cardsagainstyourliver

import android.content.ContentValues.TAG
import android.util.Log

fun main(){
    //nur zum testen erstellt
    val schwimmen1 = SchwimmenClass()
    Log.i(TAG, "Schwimmen Objekt: ${schwimmen1.toString()}")

}

class SchwimmenClass (): SpielClass(1,"Schwimmen",2,9,"Schwimmen Regeln...",false) {
    override fun toString():String{return "$id, $name, $spielerAnzahlMin, $spielerAnzahlMax, $regeln, $promilleModus"}

}