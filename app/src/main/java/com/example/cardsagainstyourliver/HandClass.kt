package com.example.cardsagainstyourliver

class HandClass(var handKarten: MutableList<KarteClass>) {

    /* fun nimm(karte: Int) {

    } */

    fun clear() {
        handKarten.clear()
    }

    fun contains(karte: KarteClass) = handKarten.contains(karte)

    override fun toString() = handKarten.joinToString()
}