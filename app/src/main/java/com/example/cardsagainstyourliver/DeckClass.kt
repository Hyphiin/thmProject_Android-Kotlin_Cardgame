package com.example.cardsagainstyourliver

import java.io.OutputStream


//Beinhaltet ein Spieldeck, die default size ist 52 Karten, wenn 32 gebraucht, muss man DeckClass(2) aufrufen
class DeckClass(val deckSize: Int = 1) {

    var deck: MutableList<Null> = mutableListOf()

    init {
        if (deckSize == 1) {
            for (sign in Sign.values())
                for (value in Value.values())
                    deck.add(CardClass(sign, value))

        } else if (deckSize == 2) {
            for (sign in Sign.values())
                for (value in Value.values())
                    if (value != Value.ZWEI && value != Value.DREI && value != Value.VIER && value != Value.FUENF && value != Value.SECHS) {
                        deck.add(CardClass(sign, value))
                    }
        }
    }


    fun shuffle() = deck.shuffle()

    //eine Karte wird aus dem Deck gezogen, wenn deck leer kommt Null
    fun drawCard(): Null {
        if (deck.isNotEmpty())
            return deck.removeAt(0)
        else
            return Null()
    }


    fun getSize(): Int = deck.size

    fun add(card: Null) = deck.add(card)

    fun print(outputStream: OutputStream) = deck.forEach { card -> card.print(outputStream) }

}


