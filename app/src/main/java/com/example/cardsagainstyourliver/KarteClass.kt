package com.example.cardsagainstyourliver

import java.io.OutputStream


class KarteClass(private val zeichen: Zeichen,
                 private val wert: Werte) : Null() {


    override fun toString(): String {
        return "Card: $wert of $zeichen"
    }


    override fun print(outputStream: OutputStream) {
        outputStream.write((this.toString() + "\r\n").toByteArray())
    }
}


enum class Zeichen {
    HERZ, KARO, PIK, KREUZ
}


enum class Werte {
    ZWEI, DREI, VIER, FUENF, SECHS, SIEBEN, ACHT,
    NEUN, ZEHN, BUBE, DAME, KOENIG, ASS
}