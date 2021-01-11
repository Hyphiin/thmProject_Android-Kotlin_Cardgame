package com.example.cardsagainstyourliver

//hier soll dann auch Bettler integriert werden... sodass eine GameClass besteht, welche alle Games starten und enden kann
abstract class GameClass(val id: Int, val name: String, val playerMin:Int, val playerMax:Int, var rules:String, var promilleModus:Boolean) {

    fun startGame(game:GameClass){
        if (game.name === "Schwimmen"){

        }
    }

    fun endGame(){

    }

}