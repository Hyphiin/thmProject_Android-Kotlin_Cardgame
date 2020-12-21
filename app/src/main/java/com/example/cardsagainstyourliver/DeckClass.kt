package com.example.cardsagainstyourliver

class DeckClass (val id:Int, var deck:Any = "default", var deckgroesse:String = "default") {

    init {
        when(id) {
            1 -> {
                deck = Array(52) { i -> KarteClass(i) }
                deckgroesse = "Großes Deck"
                deck.forEach(println() -> $it)
            }
            2 -> {
               // deck = Array(32) {KarteClass(6-13), KarteClass(19-26), KarteClass(32-39), KarteClass(45-52)}
                deckgroesse = "Kleines Deck"
            }
        }
    }


    fun mischen(){
        deck.forEach{println("$it")}
    }

    fun austeilen(){

    }

    override fun toString():String{return "$id, $deckgroesse, $deck.joinToString()"}

}

