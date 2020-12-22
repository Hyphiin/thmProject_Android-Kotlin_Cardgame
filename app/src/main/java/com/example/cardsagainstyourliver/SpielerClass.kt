package com.example.cardsagainstyourliver



class SpielerClass(val id:Int, val spielerName:String, val groeße:Double, val gewicht:Double, val alter:Int, val geschlecht:String) {
    val spielerStats: SpielerStatsClass
    val handKartenDeck = DeckClass()
    /*var zwischen : MutableList<KarteClass> = mutableListOf((handKartenDeck.karteZiehen())
    var hand : HandClass */

    init {
        spielerStats = SpielerStatsClass()
        hand = HandClass(zwischen)
    }

    fun anzahlKarten() = hand.size()


    fun clear() {
        hand.clear()
        spielerStats.löschen()
    }
}


