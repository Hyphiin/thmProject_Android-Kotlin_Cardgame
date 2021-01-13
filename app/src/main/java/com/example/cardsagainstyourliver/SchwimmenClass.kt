package com.example.cardsagainstyourliver

import android.content.ContentValues.TAG
import android.util.Log

fun main(){
    //nur zum testen erstellt
    val game = SchwimmenClass()
    game.startGame(game)

}

class SchwimmenClass(): GameClass(1,"Schwimmen",2,9,"Schwimmen Regeln...",false) {

    init{
       /*val deck = DeckClass(2)
        deck.shuffle()
        val hand1 = HandClass(deck, "Schwimmen")
        val hand2= HandClass(deck, "Null")
        val table= HandClass(deck, "Null")
        startHand(hand2,table, deck)
        val dump = HandClass(deck, "Null")*/

    }

    //die für Schwimmen spezifische Starthandabfrage --- noch nicht im Einsatz
    fun startHand(hand2:HandClass, table:HandClass, deck:DeckClass){
        val hand2a = HandClass(deck, "Schwimmen") //durch Click muss ausgewählt werden, welche Hand gezeigt wird
        val hand2b = HandClass(deck, "Schwimmen") //und dann muss Entscheidung fallen, behalten oder anderes nehmen (a = true/false)
        val a = false
        if (a == true){
            hand2.add(hand2a.getCard(0))
            hand2.add(hand2a.getCard(1))
            hand2.add(hand2a.getCard(2))

            table.add(hand2b.getCard(0))
            table.add(hand2b.getCard(1))
            table.add(hand2b.getCard(2))

        }else {
            hand2.add(hand2b.getCard(0))
            hand2.add(hand2b.getCard(1))
            hand2.add(hand2b.getCard(2))

            table.add(hand2a.getCard(0))
            table.add(hand2a.getCard(1))
            table.add(hand2a.getCard(2))
        }

    }

    //Eine Handkarte mit einer auf dem Tisch tauschen
    fun changeCard(handCard:Null, tableCard:Null, hand:HandClass, table:HandClass){
        hand.delete(handCard)
        hand.add(tableCard)
        table.delete(tableCard)
        table.add(handCard)
    }

    //Spiel beenden --- noch nicht im Einsatz
    fun close(player1:PlayerClass, player2:PlayerClass, hand1:HandClass, hand2:HandClass, close:Boolean = false){  //Zug von Spieler wird beendet, noch nicht fertig
        val playerClose = player1.playerName
        if (close == true){
            //endGame(hand1,hand2)
        }
    }

    //Spielende, kann den Gewinner sagen --- noch nicht vollständig mit Herzangabe im Einsatz
    fun endGame(hand1:HandClass, hand2:HandClass){
        if (hand1.getValueHand(hand1) > hand2.getValueHand(hand2)){
            println("Gewinner ist Hand1!")
        }else if (hand1.getValueHand(hand1) < hand2.getValueHand(hand2)){
            println("Gewinner ist Hand2!")
        }else {
            println("Oha, Unentschieden!")
        }

        /*if (lostPlayer == player1){
            if (player1.herzen > 0){
                	//herz abziehen
                }else if (player1.herzen = 0){
                	//schwimmen
            	}else {
                 	//spiel Beenden, gewinner ist player2
            	}
        }else if (player2.herzen > 0){
                	//herz abziehen
                }else if (player2.herzen = 0){
                	//schwimmen
            	}else {
                 	//spiel Beenden, gewinner ist player1
            	}
         }*/

    }

    //Anfrage zum Schieben, noch nicht fertig
    fun push(table:HandClass, dump:HandClass, deck:DeckClass, push:Boolean = false){ //Anfrage zum Schieben, noch nicht fertig
        //val playerID = player.playerName
        if (push == true){
            toDump(table, dump, deck)
        }
    }

    //Ablagestapel mischen und das Deck wieder auffüllen
    fun shuffleDump(dump:HandClass, deck:DeckClass){
        for(i in 0..(dump.getSize()-1)){
            deck.add(dump.getCard(i))
        }
    }

    //Die Tischkarten dem Ablagestapel hinzufügen und neue aus dem Deck holen
    fun toDump(table:HandClass, dump:HandClass, deck:DeckClass){
        dump.add(table.getCard(0))
        dump.add(table.getCard(1))
        dump.add(table.getCard(2))
        table.clear()
        for (i in 0..2){
            if (deck.getSize() >= 1){
                table.take()
            }else{
                shuffleDump(dump, deck)
                table.take()
            }
        }
    }

    override fun toString():String{return "$id, $name, $playerMin, $playerMax, $rules, $promilleModus"}

}