package com.example.cardsagainstyourliver

import java.io.OutputStream



class DeckClass(val deckgröße:Int = 1) {

   // if (deckgröße == 1){
        var deck: MutableList<KarteClass> = mutableListOf()

        init {
            for (zeichen in Zeichen.values())
                for (wert in Werte.values())
                    deck.add(KarteClass(zeichen, wert))
        }
   /* }else {
        var deck: MutableList<KarteClass> = mutableListOf()

        init {
            for (zeichen in Zeichen.values())
                for (wert in Werte.values())
                    if (wert != ZWEI ||wert != DREI ||wert != VIER ||wert != FÜNF || wert != SECHS){
                        cards.add(Card(zeichen, wert))
                    }
        }
    } */


    fun shuffle() = deck.shuffle()


    fun karteZiehen(): Null {
        if (deck.isNotEmpty())
            return deck.removeAt(0)
        else
            return Null()
    }

    /*fun kartenAusteilen(spielerAnzahl:Int): Null{

    } */


    fun getSize(): Int = deck.size


    fun print(outputStream: OutputStream) = deck.forEach { karte -> karte.print(outputStream) }

}


