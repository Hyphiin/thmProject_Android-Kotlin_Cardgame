package com.example.cardsagainstyourliver

import java.io.OutputStream



class DeckClass(val deckSize:Int = 1) {

   // if (deckSize == 1){
        var deck: MutableList<CardClass> = mutableListOf()

        init {
            for (sign in Sign.values())
                for (value in Value.values())
                    deck.add(CardClass(sign, value))
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


    fun drawCard(): Null {
        if (deck.isNotEmpty())
            return deck.removeAt(0)
        else
            return Null()
    }

    /*fun kartenAusteilen(spielerAnzahl:Int): Null{

    } */


    fun getSize(): Int = deck.size


    fun print(outputStream: OutputStream) = deck.forEach { card -> card.print(outputStream) }

}


