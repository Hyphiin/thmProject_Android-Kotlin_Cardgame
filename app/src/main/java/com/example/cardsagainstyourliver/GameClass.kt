package com.example.cardsagainstyourliver

abstract class GameClass(val id: Int, val name: String, val playerMin:Int, val playerMax:Int, var rules:String, var promilleModus:Boolean) {

    fun startGame(game:GameClass){
        if (game.name === "Schwimmen"){

        }
    }

    fun endGame(){

    }

}