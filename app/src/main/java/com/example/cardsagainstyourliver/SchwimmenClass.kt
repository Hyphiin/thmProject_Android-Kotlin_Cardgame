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
        val deck = DeckClass(2)
        deck.shuffle()
        val hand1 = HandClass(deck, "Schwimmen")
        val hand2= HandClass(deck, "Null")
        val table= HandClass(deck, "Null")
        startHand(hand2,table, deck)
        val dump = HandClass(deck, "Null")
        //alles was hier kommt nur für testen in Kotlin Playground
        println(hand1.getCard(1))
        println("---------------------------")
        println("Spieler 1: "+hand1.toString())
        println("Spieler 2: "+hand2.toString())
        println("Tisch: "+table.toString())
        println("Ablagestapel: "+dump.toString())
        println("---------------------------")
        changeCard(hand1.getCard(1),table.getCard(1), hand1, table)
        println("Spieler 1: "+hand1.toString())
        println("Spieler 2: "+hand2.toString())
        println("Tisch: "+table.toString())
        println("Ablagestapel: "+dump.toString())
        println("---------------------------")
        toDump(table, dump, deck)
        println("Spieler 1: "+hand1.toString())
        println("Spieler 2: "+hand2.toString())
        println("Tisch: "+table.toString())
        println("Ablagestapel: "+dump.toString())
        println("---------------------------")

    }

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


    fun changeCard(handCard:Null, tableCard:Null, hand:HandClass, table:HandClass){
        hand.delete(handCard)
        hand.add(tableCard)
        table.delete(tableCard)
        table.add(handCard)
    }


    fun close(player1:PlayerClass, player2:PlayerClass, hand1:HandClass, hand2:HandClass, close:Boolean = false){  //Zug von Spieler wird beendet, noch nicht fertig
        val playerClose = player1.playerName
        if (close == true){
            //endGame(player1,player2,hand1,hand2)
        }
    }

    //hier muss auch noch getüftelt werden und es fehlt einfach die getValue Methode aus hand...
    /*fun endGame(player1:PlayerClass, player2:PlayerClass, hand1:HandClass, hand2:HandClass){
        val lostPlayer:PlayerClass
        if (hand1.getValue() > hand2.getValue()){
            lostPlayer = player2
        }else if (hand1.getValue() < hand2.getValue()){
            lostPlayer = player1
        }else {
            //unentschieden
        }

        if (lostPlayer == player1){
            if (player1.herzen > 0){
                	//herz abziehen
                }else if (player1.herzen = 0){
                	//schwimmen
            	}else {
                 	//spiel Beenden, gewinner ist player2
            	}
            }
        }else{
        	if (player2.herzen > 0){
                	//herz abziehen
                }else if (player2.herzen = 0){
                	//schwimmen
            	}else {
                 	//spiel Beenden, gewinner ist player1
            	}
            }

        }
    }*/

    fun push(player:PlayerClass, table:HandClass, dump:HandClass, deck:DeckClass, push:Boolean = false){ //Anfrage zum Schieben, noch nicht fertig
        val playerID = player.playerName
        if (push == true){
            toDump(table, dump, deck)
        }
    }

    fun shuffleDump(dump:HandClass, deck:DeckClass){
        for(i in 0..(dump.getSize())){
            deck.add(dump.getCard(i))
        }
    }

    fun toDump(table:HandClass, dump:HandClass, deck:DeckClass){
        dump.add(table.getCard(0))
        dump.add(table.getCard(1))
        dump.add(table.getCard(2))
        table.clear()
        for (i in 1..3){
            if (deck.getSize() > 0){
                table.take()
            }else{
                shuffleDump(dump, deck)
                table.take()
            }
        }
    }

    override fun toString():String{return "$id, $name, $playerMin, $playerMax, $rules, $promilleModus"}

}