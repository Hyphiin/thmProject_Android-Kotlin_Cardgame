package com.example.cardsagainstyourliver

class HandClass(var hand: MutableList<CardClass>) {

    /* fun nimm(karte: Int) {

    } */

    fun clear() {
        hand.clear()
    }

    fun contains(karte: CardClass) = hand.contains(karte)

    override fun toString() = hand.joinToString()
}